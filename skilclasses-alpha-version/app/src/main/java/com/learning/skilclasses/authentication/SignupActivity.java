package com.learning.skilclasses.authentication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.Task;
import com.learning.skilclasses.R;
import com.learning.skilclasses.models.UserDetails;
import com.learning.skilclasses.Utilities.ApiUrl;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "LoginActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private OkHttpClient okHttpClient;
    private String course;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnClick(R.id.signup)
    void openSignup() {

        if (TextUtils.isEmpty(editTexttName.getText().toString().trim())) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextUsername.getText().toString().trim())) {
            Toast.makeText(this, "Email cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPassword.getText().toString().trim())) {
            Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Objects.equal(editTextPassword.getText().toString().trim(), editTextCpassword.getText().toString().trim())) {
            Toast.makeText(this, "Password not matching...", Toast.LENGTH_SHORT).show();
            return;
        } else {
            UserDetails userDetails = new UserDetails(editTexttName.getText().toString().trim(), editTextUsername.getText().toString().trim(), editTextPassword.getText().toString().trim(), "false", course);
            progressBar.setVisibility(View.VISIBLE);
            btnSignup.setText("Registering...");
            btnSignup.setEnabled(false);
            new Thread(() -> {
                try {
                    String response = updateLogin(ApiUrl.SIGN_UP_USER, userDetails);
                    //     Log.d("response", "this is response" + response);
                    JSONObject jsonObject = new JSONObject(response);
                    final boolean status = jsonObject.getBoolean("status");
                    if (status) {
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        });
                    } else {
                        runOnUiThread(() -> {
                            Toast.makeText(this, "" + "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                            btnSignup.setText("Signup");
                            btnSignup.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        btnSignup.setText("Signup");
                        btnSignup.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    });
                }
            }).start();
        }
    }

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;


    @BindView(R.id.name)
    EditText editTexttName;
    @BindView(R.id.username)
    EditText editTextUsername;
    @BindView(R.id.password)
    EditText editTextPassword;
    @BindView(R.id.cpassword)
    EditText editTextCpassword;


    @OnClick(R.id.sign_in_button)
    void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @BindView(R.id.signup)
    Button btnSignup;

    @OnClick(R.id.login)
    void openSignin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent intent = getIntent();
        course = intent.getStringExtra("course");
        Toast.makeText(this, "" + course, Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            try {
                updateUI(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        try {
            TextView textView = ((TextView) signInButton.getChildAt(0));
            Typeface typeface = ResourcesCompat.getFont(this, R.font.muli);
            textView.setText("Continue with Google");
            textView.setTypeface(typeface);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void updateUI(GoogleSignInAccount account) throws Exception {
        mGoogleSignInClient.signOut();
        Log.d("Name", account.getDisplayName());
        Log.d("Email", account.getEmail());
        UserDetails userDetails = new UserDetails(account.getDisplayName(), account.getEmail(), "", "false", course);
        String response = updateLogin(ApiUrl.SIGN_UP_USER, userDetails);
        JSONObject jsonObject = new JSONObject(response);
        final boolean status = jsonObject.getBoolean("status");
        if (status) {
            runOnUiThread(() -> {
                Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            });
        } else {
            runOnUiThread(() -> {
                Toast.makeText(this, "" + "Something went wrong !!!", Toast.LENGTH_SHORT).show();
                btnSignup.setText("Signup");
                btnSignup.setEnabled(true);
                progressBar.setVisibility(View.GONE);
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                handleSignInResult(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) throws Exception {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private String updateLogin(String url, UserDetails userDetails) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", userDetails.getUsername())
                .addFormDataPart("password", userDetails.getPassword())
                .addFormDataPart("name", userDetails.getName())
                .addFormDataPart("login", userDetails.getLogin())
                .addFormDataPart("course", userDetails.getCourse())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
