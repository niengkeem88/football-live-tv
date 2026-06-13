package com.footballlive.tv.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;
import com.footballlive.tv.data.local.Prefs;
import com.footballlive.tv.ui.terms.TermsActivity;

import java.util.Arrays;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private String selected = null;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_location);

        List<String> continents = Arrays.asList(
            "Africa", "Asia", "Europe", "North America", "South America", "Oceania");

        TextView next = findViewById(R.id.locNext);
        next.setVisibility(View.GONE);
        next.setOnClickListener(v -> proceed());

        RecyclerView rv = findViewById(R.id.locGrid);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(new ContinentAdapter(continents, name -> {
            selected = name;
            next.setVisibility(View.VISIBLE);
        }));
    }

    private void proceed() {
        if (selected == null) return;
        new Prefs(this).setContinent(selected);
        startActivity(new Intent(this, TermsActivity.class));
        finish();
    }
}