package com.learning.skilclasses.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.learning.skilclasses.R;
import com.learning.skilclasses.authentication.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityGrade extends AppCompatActivity {
    //
    @BindView(R.id.group_choices)
    RadioGroup group;

    @BindView(R.id.choice_a)
    RadioButton toggleA;

    @BindView(R.id.choice_b)
    RadioButton toggleB;

    @BindView(R.id.choice_c)
    RadioButton toggleC;

    @BindView(R.id.choice_d)
    RadioButton toggleD;

    @BindView(R.id.choice_e)
    RadioButton toggleE;

    @BindView(R.id.choice_f)
    RadioButton toggleF;

    @BindView(R.id.choice_g)
    RadioButton toggleG;

    @BindView(R.id.choice_h)
    RadioButton toggleH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ButterKnife.bind(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent intent = new Intent(ActivityGrade.this, SignupActivity.class);
                switch (checkedId) {
                    case R.id.choice_a:
                        intent.putExtra("course", "9");
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleA.setTextColor(Color.WHITE);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        break;
                    case R.id.choice_b:
                        intent.putExtra("course", "10");
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleB.setTextColor(Color.WHITE);
                        break;
                    case R.id.choice_c:
                        intent.putExtra("course", toggleC.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleC.setTextColor(Color.WHITE);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        break;
                    case R.id.choice_d:
                        intent.putExtra("course", toggleD.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleD.setTextColor(Color.WHITE);
                        break;
                    case R.id.choice_e:
                        intent.putExtra("course", toggleE.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleE.setTextColor(Color.WHITE);
                        break;
                    case R.id.choice_f:
                        intent.putExtra("course", toggleF.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleF.setTextColor(Color.WHITE);
                        break;
                    case R.id.choice_g:
                        intent.putExtra("course", toggleG.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleH.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleG.setTextColor(Color.WHITE);
                        break;
                    case R.id.choice_h:
                        intent.putExtra("course", toggleH.getText().toString().trim());
                        startActivity(intent);
                        finish();
                        toggleA.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleA.setTextColor(Color.BLACK);
                        toggleC.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleC.setTextColor(Color.BLACK);
                        toggleD.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleD.setTextColor(Color.BLACK);
                        toggleF.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleF.setTextColor(Color.BLACK);
                        toggleG.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleG.setTextColor(Color.BLACK);
                        toggleB.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleB.setTextColor(Color.BLACK);
                        toggleE.setBackground(getDrawable(R.drawable.checked_grade_background));
                        toggleE.setTextColor(Color.BLACK);
                        toggleH.setBackground(getDrawable(R.drawable.checked_grade_background1));
                        toggleH.setTextColor(Color.WHITE);
                        break;
                }
            }
        });
    }
}
