package com.example.techclub.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.valdesekamdem.library.mdtoast.MDToast;

public class Register_page extends AppCompatActivity {

    Button signup;
    TextView login_textview,Registering,registertextview;
    EditText username,password,confirm_password;
    String user_name,pass_word,Confirm_password;
    private Dialog loginDialog;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        registertextview=findViewById(R.id.registertextview);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.animation);
        registertextview.startAnimation(myanim);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        login_textview=findViewById(R.id.login_textview);
        signup=findViewById(R.id.signup);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginDialog = new Dialog(this);
        confirm_password=findViewById(R.id.Conform_Password);
        loginDialog.setContentView(R.layout.progressdiolog);
        progressBar = loginDialog.findViewById(R.id.registerProgress);
        Registering = loginDialog.findViewById(R.id.registering);
        loginDialog.setCancelable(false);
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        firebaseAuth = FirebaseAuth.getInstance();
        login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register_page.this,Login_page.class);
                startActivity(intent);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name=username.getText().toString();
                pass_word=password.getText().toString();
                Confirm_password=confirm_password.getText().toString();
                loginDialog.show();
                Registering.setText("Registering...");
                progressBar.setVisibility(View.VISIBLE);
                if(validate()){
                    firebaseAuth.createUserWithEmailAndPassword(user_name, pass_word).addOnCompleteListener(Register_page.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                username.setText("");
                                password.setText("");
                                confirm_password.setText("");
                                MDToast.makeText(getApplicationContext(), "SignUp unsuccessful: ",MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
                                progressBar.setVisibility(View.GONE);
                                loginDialog.dismiss();
                            } else {
                                username.setText("");
                                password.setText("");
                                confirm_password.setText("");
                                MDToast.makeText(getApplicationContext(), "SignUp successful: ",MDToast.LENGTH_SHORT,MDToast.TYPE_SUCCESS);
                                progressBar.setVisibility(View.GONE);
                                loginDialog.dismiss();
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                Toast.makeText(Register_page.this, "An Email is sent Your Email, Follow Instruction To Verify Your Account", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }
                        }
                    });

                }
            }
        });
    }
    Boolean validate(){
        Boolean flag=false;
        if(TextUtils.isEmpty(username.getText())||TextUtils.isEmpty(password.getText())||TextUtils.isEmpty(confirm_password.getText())){
            MDToast.makeText(this,"Field cannot be Empty",MDToast.LENGTH_SHORT,MDToast.TYPE_WARNING);
            flag=false;
            progressBar.setVisibility(View.GONE);
            loginDialog.dismiss();
        }
        else if(!username.getText().toString().contains("@")){
            MDToast.makeText(this,"Incorrect Email",MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
            flag=false;
            progressBar.setVisibility(View.GONE);
            loginDialog.dismiss();
        }
        else if(!(pass_word.equals(Confirm_password))){
            MDToast.makeText(this,"Password Mismatched",MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
            flag=false;
            progressBar.setVisibility(View.GONE);
            loginDialog.dismiss();
        }
        else{
            flag=true;
        }
        return flag;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
