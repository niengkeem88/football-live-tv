package com.footballlive.tv.ui.terms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.footballlive.tv.R;
import com.footballlive.tv.data.local.Prefs;
import com.footballlive.tv.ui.landing.LandingActivity;

public class TermsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_terms);

        CheckBox agree = findViewById(R.id.termsAgree);
        Button accept = findViewById(R.id.termsAccept);
        accept.setEnabled(false);

        agree.setOnCheckedChangeListener((b, checked) -> accept.setEnabled(checked));
        accept.setOnClickListener(v -> {
            new Prefs(this).setTermsAccepted(true);
            startActivity(new Intent(this, LandingActivity.class));
            finish();
        });
    }
}