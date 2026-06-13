package com.footballlive.tv;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import com.footballlive.tv.data.local.Prefs;
import com.google.android.gms.ads.MobileAds;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Apply saved dark-mode preference app-wide
        Prefs prefs = new Prefs(this);
        AppCompatDelegate.setDefaultNightMode(prefs.isDarkMode()
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);
        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});
    }
}