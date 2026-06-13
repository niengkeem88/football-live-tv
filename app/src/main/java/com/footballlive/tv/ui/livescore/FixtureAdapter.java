package com.footballlive.tv.ui.livescore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.footballlive.tv.R;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureItem;

import java.util.List;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.VH> {
    private final List<FixtureItem> items;
    public FixtureAdapter(List<FixtureItem> items) { this.items = items; }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fixture, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        FixtureItem it = items.get(pos);
        h.league.setText(it.league != null ? it.league.name : "");
        if (it.teams != null) {
            h.homeName.setText(it.teams.home != null ? it.teams.home.name : "");
            h.awayName.setText(it.teams.away != null ? it.teams.away.name : "");
            if (it.teams.home != null)
                Glide.with(h.itemView).load(it.teams.home.logo).into(h.homeLogo);
            if (it.teams.away != null)
                Glide.with(h.itemView).load(it.teams.away.logo).into(h.awayLogo);
        }
        String score = "-";
        if (it.goals != null && it.goals.home != null && it.goals.away != null) {
            score = it.goals.home + " : " + it.goals.away;
        }
        h.score.setText(score);
        h.status.setText(statusText(it));
    }

    private String statusText(FixtureItem it) {
        if (it.fixture == null || it.fixture.status == null) return "";
        String sh = it.fixture.status.shortStatus;
        Integer min = it.fixture.status.elapsed;
        if ("1H".equals(sh) || "2H".equals(sh)) return (min != null ? min + "'" : "LIVE");
        if ("HT".equals(sh)) return "HT";
        if ("FT".equals(sh)) return "FT";
        return sh != null ? sh : "";
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView league, homeName, awayName, score, status;
        ImageView homeLogo, awayLogo;
        VH(View v) {
            super(v);
            league = v.findViewById(R.id.fxLeague);
            homeName = v.findViewById(R.id.fxHomeName);
            awayName = v.findViewById(R.id.fxAwayName);
            score = v.findViewById(R.id.fxScore);
            status = v.findViewById(R.id.fxStatus);
            homeLogo = v.findViewById(R.id.fxHomeLogo);
            awayLogo = v.findViewById(R.id.fxAwayLogo);
        }
    }
}