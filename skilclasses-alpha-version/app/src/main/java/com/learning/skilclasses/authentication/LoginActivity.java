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
import com.google.android.gms.tasks.Task;
import com.learning.skilclasses.R;
import com.learning.skilclasses.Utilities.ApiUrl;
import com.learning.skilclasses.activities.ActivityGrade;
import com.learning.skilclasses.activities.MainActivity;
import com.learning.skilclasses.models.UserDetails;
import com.learning.skilclasses.preferences.UserSession;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "LoginActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private OkHttpClient okHttpClient;
    private UserSession userSession;
    private static final String PREFER_NAME = "login_preference";

    @OnClick(R.id.signup)
    void openSignup() {
        startActivity(new Intent(this, ActivityGrade.class));
        finish();
    }

    private boolean isLoginEnabled = false;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    @BindView(R.id.username)
    EditText editTextUsername;

    @BindView(R.id.password)
    EditText editTextPassword;

    @BindView(R.id.login)
    Button btnLogin;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @OnClick(R.id.login)
    void loginUser() {
        if (TextUtils.isEmpty(editTextUsername.getText().toString())) {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setText("Logging...");
            btnLogin.setEnabled(false);
            new Thread(() -> {
                try {
                    String response = updateLogin(ApiUrl.LOGIN_USER, new UserDetails(editTextUsername.getText().toString().trim(), editTextPassword.getText().toString().trim()));
                    Log.d("response", "this is response" + response);
                    JSONObject jsonObject = new JSONObject(response);
                    final boolean status = jsonObject.getBoolean("status");
                    if (status) {
                        String statusUpdate = updateLoginStatus(ApiUrl.UPDATE_LOGIN_STATUS, jsonObject.getString("id"), "true");
                        JSONObject jsonObject1 = new JSONObject(statusUpdate);
                        if (jsonObject1.getString("id").equals(jsonObject.getString("id"))) {
                            Log.d("response", "this is response" + response);
                            runOnUiThread(() -> {
                                try {
                                    isLoginEnabled = false;
                                    btnLogin.setText("Login");
                                    progressBar.setVisibility(View.GONE);
                                    userSession.createUserLoginSession(jsonObject.getString("id"), jsonObject.getString("name"), jsonObject.getString("username"), "false", jsonObject.getString("course"));
                                    startActivity(new Intent(this, MainActivity.class));
                                    finish();
                                } catch (Exception e) {
                                    btnLogin.setText("Login");
                                    btnLogin.setEnabled(true);
                                    progressBar.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            btnLogin.setText("Login");
                            btnLogin.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "Already Logged in somewhere", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        runOnUiThread(() -> {
                            Toast.makeText(this, "" + "Invalid username or password", Toast.LENGTH_SHORT).show();
                            btnLogin.setText("Login");
                            btnLogin.setEnabled(true);
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        btnLogin.setText("Login");
                        btnLogin.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    });
                }
            }).start();
        }
    }

    @OnClick(R.id.sign_in_button)
    void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            textView.setText("Sign in with Google");
            textView.setTypeface(typeface);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        userSession = new UserSession(this);

    }

    private void updateUI(GoogleSignInAccount account) throws Exception {

        String response = updateLogin(ApiUrl.CHECK_GOOGLE_ACCOUNT, new UserDetails(account.getEmail()), 1);
        Log.d("response", "this is response" + response);
        JSONObject jsonObject = new JSONObject(response);
        boolean status = jsonObject.getBoolean("status");
        if (!status) {
            Toast.makeText(this, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            mGoogleSignInClient.signOut();
        } else if (status) {
            String loginStatus = jsonObject.getString("login");
            if (loginStatus.equals("false")) {
                String statusUpdate = updateLoginStatus(ApiUrl.UPDATE_LOGIN_STATUS, jsonObject.getString("id"), "true");
                JSONObject jsonObject1 = new JSONObject(statusUpdate);
                if (jsonObject1.getString("id").equals(jsonObject.getString("id"))) {
                    userSession.createUserLoginSession(jsonObject.getString("id"), jsonObject.getString("name"), jsonObject.getString("username"), "true", jsonObject.getString("course"));
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
            } else {
                Toast.makeText(this, "Already Logged in somewhere", Toast.LENGTH_SHORT).show();
            }
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

    private String updateLogin(String url, UserDetails userDetails, int i) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", userDetails.getUsername())
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

    private String updateLoginStatus(String url, String id, String loginStatus) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .addFormDataPart("login", loginStatus)
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


    @Override
    protected void onStart() {
        super.onStart();
        if (userSession.isUserLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
