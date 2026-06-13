package com.footballlive.tv.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.footballlive.tv.R;
import com.footballlive.tv.data.local.Prefs;
import com.footballlive.tv.ui.main.MainActivity;
import com.footballlive.tv.ui.onboarding.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Prefs prefs = new Prefs(this);
            Intent i = prefs.isFirstLaunch()
                    ? new Intent(this, OnboardingActivity.class)
                    : new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }, 1800);
    }
}