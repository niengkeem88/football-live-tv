package com.footballlive.tv.ui.livetv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;
import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.VH> {

    public static class League {
        public final int id; public final String name; public final String country;
        public League(int id, String name, String country) {
            this.id = id; this.name = name; this.country = country;
        }
    }

    public interface OnClick { void onClick(League league); }

    private final List<League> items;
    private final OnClick listener;

    public LeagueAdapter(List<League> items, OnClick listener) {
        this.items = items; this.listener = listener;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_league, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        League l = items.get(pos);
        h.name.setText(l.name);
        h.country.setText(l.country);
        h.itemView.setOnClickListener(v -> listener.onClick(l));
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, country;
        VH(View v) {
            super(v);
            name = v.findViewById(R.id.leagueName);
            country = v.findViewById(R.id.leagueCountry);
        }
    }
}