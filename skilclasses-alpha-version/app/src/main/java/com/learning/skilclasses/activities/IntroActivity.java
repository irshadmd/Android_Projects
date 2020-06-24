package com.learning.skilclasses.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.learning.skilclasses.authentication.LoginActivity;
import com.learning.skilclasses.intro_slider.IntroFragment1;
import com.learning.skilclasses.intro_slider.IntroFragment2;
import com.learning.skilclasses.intro_slider.IntroFragment3;
import com.learning.skilclasses.intro_slider.IntroFragment4;
import com.learning.skilclasses.preferences.PreferenceManager;

public class IntroActivity extends AppIntro {

    PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(this);
        if (!preferenceManager.FirstLaunch()) {
            launchMain();
        }
        addSlide(new IntroFragment1());
        addSlide(new IntroFragment2());
        addSlide(new IntroFragment3());
        addSlide(new IntroFragment4());
        setFadeAnimation();
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    private void launchMain() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchMain();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    public void setIndicatorColor(int selectedIndicatorColor, int unselectedIndicatorColor) {
        super.setIndicatorColor(Color.BLACK, Color.WHITE);
    }
}
