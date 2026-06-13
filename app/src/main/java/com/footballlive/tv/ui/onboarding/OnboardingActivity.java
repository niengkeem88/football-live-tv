package com.footballlive.tv.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.footballlive.tv.R;
import com.footballlive.tv.ui.location.LocationActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 pager;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_onboarding);

        List<Slide> slides = Arrays.asList(
            new Slide(R.drawable.onb_welcome, "Welcome to Football Live TV",
                "Experience the ultimate football platform with multiple features and live updates."),
            new Slide(R.drawable.onb_matches, "Live Matches",
                "Access multiple football live options and never miss your favorite team's match scores again."),
            new Slide(R.drawable.onb_premium, "Unlock Premium Features",
                "Live match notifications · HD quality scores · Exclusive match details · Quality experience"),
            new Slide(R.drawable.onb_started, "Get Started",
                "Join millions of football fans worldwide and start your football journey today!")
        );

        pager = findViewById(R.id.onbPager);
        pager.setAdapter(new OnboardingAdapter(slides));

        TabLayout dots = findViewById(R.id.onbDots);
        new TabLayoutMediator(dots, pager, (tab, pos) -> {}).attach();

        TextView skip = findViewById(R.id.onbSkip);
        TextView next = findViewById(R.id.onbNext);
        skip.setOnClickListener(v -> goNext());
        next.setOnClickListener(v -> {
            int pos = pager.getCurrentItem();
            if (pos < slides.size() - 1) pager.setCurrentItem(pos + 1);
            else goNext();
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                next.setText(position == slides.size() - 1
                        ? R.string.get_started : R.string.continue_label);
            }
        });
    }

    private void goNext() {
        startActivity(new Intent(this, LocationActivity.class));
        finish();
    }
}