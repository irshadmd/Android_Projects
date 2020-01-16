package com.example.juveriamanzar.e_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView miv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        miv=findViewById(R.id.logo);

        Animation mAnim= AnimationUtils.loadAnimation(this,R.anim.mtransition);
        miv.startAnimation(mAnim);

        Thread thread=new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(),UserActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
