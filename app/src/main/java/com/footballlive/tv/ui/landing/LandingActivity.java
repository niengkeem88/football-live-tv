package com.footballlive.tv.ui.landing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.footballlive.tv.R;
import com.footballlive.tv.data.local.Prefs;
import com.footballlive.tv.ui.main.MainActivity;

public class LandingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_landing);

        Prefs prefs = new Prefs(this);
        prefs.setFirstLaunch(false); // onboarding complete

        TextView selected = findViewById(R.id.landingSelected);
        selected.setText(getString(R.string.selected_continent, prefs.getContinent()));

        Button open = findViewById(R.id.landingOpen);
        open.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        findViewById(R.id.landingRate).setOnClickListener(v -> openUrl(
            "https://play.google.com/store/apps/details?id=" + getPackageName()));
        findViewById(R.id.landingShare).setOnClickListener(v -> shareApp());
        findViewById(R.id.landingPrivacy).setOnClickListener(v -> openUrl(
            "https://your-privacy-policy-url.com"));
    }

    private void openUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void shareApp() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,
            "Check out Football Live TV: https://play.google.com/store/apps/details?id="
            + getPackageName());
        startActivity(Intent.createChooser(i, "Share"));
    }
}