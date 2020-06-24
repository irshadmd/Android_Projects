package com.example.techclub.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.techclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        icon=findViewById(R.id.splashlogo);

        if(FirebaseAuth.getInstance().getCurrentUser()==null) {
            Animation myanim = AnimationUtils.loadAnimation(this, R.anim.animation);
            icon.startAnimation(myanim);
            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(2000);
                        Intent intent = new Intent(getApplicationContext(), Register_page.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        }else {
            FirebaseAuth.getInstance().getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(Splash.this, "User Refreshed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Register_page.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
