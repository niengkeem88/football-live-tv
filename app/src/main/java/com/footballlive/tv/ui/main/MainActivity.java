package com.footballlive.tv.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.footballlive.tv.R;
import com.footballlive.tv.ads.AdManager;
import com.footballlive.tv.ui.livescore.LiveScoreFragment;
import com.footballlive.tv.ui.livetv.LiveTvFragment;
import com.footballlive.tv.ui.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        AdManager.preloadInterstitial(this);

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnItemSelectedListener(item -> {
            Fragment f;
            int id = item.getItemId();
            if (id == R.id.nav_live_tv) f = new LiveTvFragment();
            else if (id == R.id.nav_settings) f = new SettingsFragment();
            else f = new LiveScoreFragment();
            return switchTo(f);
        });

        if (s == null) nav.setSelectedItemId(R.id.nav_live_score);
    }

    private boolean switchTo(@NonNull Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, f)
                .commit();
        return true;
    }
}