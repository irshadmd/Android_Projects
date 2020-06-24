package com.learning.skilclasses.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.learning.skilclasses.R;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {
    LinkedHashMap<String, String> result;
    LinearLayout linearLayout;
    int i = 1;
    TextView score;
    String userScore = "";
    ProgressBar scoreProgress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.again)
    Button play_again;

    @OnClick(R.id.again)
    void setPlay_again() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        userScore = getIntent().getStringExtra("score");
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        scoreProgress = findViewById(R.id.score_progress);
        // mBuilder.setContentTitle("Notification Alert, Click Me!");
//        if (userScore == 10) {
//            mBuilder.setContentText("Hi, Your Score is " + userScore + "   Congratulations You Scored 100%");
//        } else
//            mBuilder.setContentText("Hi, Your Score is " + userScore + "   Better Luck next time");
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(001, mBuilder.build());
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        scoreProgress.setProgress(Integer.parseInt(userScore));
        result = QuizActivity.arrayList;
        linearLayout = findViewById(R.id.main_layout);
        score = findViewById(R.id.result);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, 20, 0, 0);
        score.setText(userScore + "");
        for (Map.Entry<String, String> entry : result.entrySet()) {
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.didact_gothic);
            TextView question = new TextView(this);
            question.setTypeface(typeface, Typeface.BOLD);
            question.setLayoutParams(param1);
            question.setTextSize(13);
            question.setText("Q" + i + " " + entry.getKey());
            question.setTextColor(Color.BLACK);
            TextView answer = new TextView(this);
            answer.setText(entry.getValue());
            answer.setLayoutParams(param1);
            answer.setTextColor(getResources().getColor(R.color.correct));
            answer.setTypeface(typeface);
            answer.setTextSize(13);
            LinearLayout linearLayout1 = new LinearLayout(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayout1.setLayoutParams(param);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(question);
            linearLayout1.addView(answer);
            linearLayout.addView(linearLayout1);
            i++;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }
}
