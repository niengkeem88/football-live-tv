package com.footballlive.tv.ui.livetv;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;

import java.util.Arrays;
import java.util.List;

public class LiveTvFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle s) {
        return inflater.inflate(R.layout.fragment_live_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle s) {
        List<LeagueAdapter.League> leagues = Arrays.asList(
            new LeagueAdapter.League(39, "Premier League", "England"),
            new LeagueAdapter.League(140, "La Liga", "Spain"),
            new LeagueAdapter.League(135, "Serie A", "Italy"),
            new LeagueAdapter.League(78, "Bundesliga", "Germany"),
            new LeagueAdapter.League(61, "Ligue 1", "France"),
            new LeagueAdapter.League(2, "UEFA Champions League", "Europe"),
            new LeagueAdapter.League(3, "UEFA Europa League", "Europe"),
            new LeagueAdapter.League(848, "UEFA Conference League", "Europe")
        );

        RecyclerView rv = view.findViewById(R.id.tvList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new LeagueAdapter(leagues, league -> {
            Intent i = new Intent(getContext(), FixturesActivity.class);
            i.putExtra(FixturesActivity.EXTRA_LEAGUE_ID, league.id);
            i.putExtra(FixturesActivity.EXTRA_LEAGUE_NAME, league.name);
            startActivity(i);
        }));
    }
}