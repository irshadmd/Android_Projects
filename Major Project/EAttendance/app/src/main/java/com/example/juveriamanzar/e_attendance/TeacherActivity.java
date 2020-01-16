package com.example.juveriamanzar.e_attendance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {

    private Button mlbtn, msbtn;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mlbtn = findViewById(R.id.lbtn);
        msbtn = findViewById(R.id.sbtn);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new TSignInFragment());
        fragmentTransaction.commit();

        mlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.up_in_btns_design);
                mlbtn.setTextColor(Color.WHITE);
                if (count != 0) {
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new TSignInFragment());
                    fragmentTransaction.commit();
                }
            }
        });

        msbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundResource(R.drawable.up_in_btns_design);
                mlbtn.setTextColor(Color.WHITE);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, new TSignUpFragment());
                fragmentTransaction.commit();
                count++;
            }
        });

    }

    /*public void fragmentSelect(View v) {

        switch (v.getId())
        {
            case R.id.lbtn:
                v.setBackgroundResource(R.drawable.up_in_btns_design);
                mlbtn.setTextColor(Color.WHITE);
                break;

            case R.id.sbtn:
                v.setBackgroundResource(R.drawable.up_in_btns_design);
                msbtn.setTextColor(Color.WHITE);
                break;

        }
    }*/
}
