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

import com.example.techclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.valdesekamdem.library.mdtoast.MDToast;

public class Login_page extends AppCompatActivity {

    Button signin;
    TextView register_textview,Registering,logintextview;
    EditText username,password;
    String user_name,pass_word;
    private Dialog loginDialog;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        logintextview=findViewById(R.id.logintextview);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.animation);
        logintextview.startAnimation(myanim);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        signin=findViewById(R.id.signin);
        register_textview=findViewById(R.id.register_textview);
        username=findViewById(R.id.username);
        password=findViewById(R.id.Password);
        loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.progressdiolog);
        // view2 = getLayoutInflater().inflate(R.layout.progressdialog, null);
        // builder = new AlertDialog.Builder(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = loginDialog.findViewById(R.id.registerProgress);
        Registering = loginDialog.findViewById(R.id.registering);
        loginDialog.setCancelable(false);
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name=username.getText().toString();
                pass_word=password.getText().toString();
                loginDialog.show();
                Registering.setText("Logging...");
                progressBar.setVisibility(View.VISIBLE);
                if(validate()){
                    firebaseAuth.signInWithEmailAndPassword(user_name,pass_word).addOnCompleteListener(Login_page.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                username.setText("");
                                password.setText("");
                                MDToast.makeText(getApplicationContext(), "Login unsuccessful: ",MDToast.LENGTH_SHORT,MDToast.TYPE_ERROR);
                                progressBar.setVisibility(View.GONE);
                                loginDialog.dismiss();
                            } else {
                                username.setText("");
                                password.setText("");
                                MDToast.makeText(getApplicationContext(), "Login successful: ",MDToast.LENGTH_SHORT,MDToast.TYPE_SUCCESS);
                                progressBar.setVisibility(View.GONE);
                                loginDialog.dismiss();
                                startActivity(new Intent(Login_page.this,MainActivity.class));
                                finish();
                            }
                        }
                    });
                }

            }
        });
        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_page.this,Register_page.class);
                startActivity(intent);
            }
        });
    }
    Boolean validate(){
        Boolean flag=false;
        if(TextUtils.isEmpty(user_name)||TextUtils.isEmpty(pass_word)){
            MDToast.makeText(this,"field cannot be Empty",MDToast.LENGTH_SHORT,MDToast.TYPE_INFO);
            flag=false;
            progressBar.setVisibility(View.GONE);
            loginDialog.dismiss();
        }
        else{
            flag=true;
        }
        return flag;
    }
}
