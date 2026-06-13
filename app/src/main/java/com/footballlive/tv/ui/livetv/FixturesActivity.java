package com.footballlive.tv.ui.livetv;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;
import com.footballlive.tv.ads.AdManager;
import com.footballlive.tv.data.remote.RetrofitClient;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureItem;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureResponse;
import com.footballlive.tv.ui.livescore.FixtureAdapter;
import com.footballlive.tv.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixturesActivity extends AppCompatActivity {
    public static final String EXTRA_LEAGUE_ID = "league_id";
    public static final String EXTRA_LEAGUE_NAME = "league_name";

    private final List<FixtureItem> data = new ArrayList<>();
    private FixtureAdapter adapter;
    private TextView empty;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_fixtures);

        int leagueId = getIntent().getIntExtra(EXTRA_LEAGUE_ID, 39);
        String name = getIntent().getStringExtra(EXTRA_LEAGUE_NAME);

        TextView title = findViewById(R.id.fixturesTitle);
        title.setText(name != null ? name : "Fixtures");
        findViewById(R.id.fixturesBack).setOnClickListener(v -> finish());

        empty = findViewById(R.id.fixturesEmpty);
        RecyclerView rv = findViewById(R.id.fixturesList);
        adapter = new FixtureAdapter(data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        // Interstitial on entering a league (AdMob applies its own frequency cap)
        AdManager.showInterstitial(this);

        load(leagueId);
    }

    private void load(int leagueId) {
        RetrofitClient.get().getFixturesByLeague(leagueId, Constants.CURRENT_SEASON)
                .enqueue(new Callback<FixtureResponse>() {
            @Override
            public void onResponse(@NonNull Call<FixtureResponse> call,
                                   @NonNull Response<FixtureResponse> resp) {
                data.clear();
                if (resp.body() != null && resp.body().response != null)
                    data.addAll(resp.body().response);
                adapter.notifyDataSetChanged();
                empty.setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
            }
            @Override
            public void onFailure(@NonNull Call<FixtureResponse> call, @NonNull Throwable t) {
                empty.setVisibility(View.VISIBLE);
            }
        });
    }
}