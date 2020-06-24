package com.learning.skilclasses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.learning.skilclasses.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.logo)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.logo).into(imageView);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
        }, 000);
    }
}
