package com.pk.bollypedia.a4in1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button btn1=(Button)findViewById(R.id.btnyou);
         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent1=new Intent(getApplicationContext(),Act2.class);
                 startActivity(intent1);
             }
         });

         Button btn2=(Button)findViewById(R.id.btn2);
         btn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent2=new Intent(getApplicationContext(),Act3.class);
                 startActivity(intent2);
             }
         });


        Button btn3=(Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(getApplicationContext(),Act4.class);
                startActivity(intent3);
            }
        });

        Button btn4=(Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(getApplicationContext(),Act5.class);
                startActivity(intent4);
            }
        });
    }
}
