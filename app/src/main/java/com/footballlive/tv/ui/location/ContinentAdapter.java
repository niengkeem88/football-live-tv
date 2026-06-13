package com.footballlive.tv.ui.location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;
import java.util.List;

public class ContinentAdapter extends RecyclerView.Adapter<ContinentAdapter.VH> {
    public interface OnSelect { void onSelect(String name); }

    private final List<String> items;
    private final OnSelect listener;
    private int selectedPos = -1;

    public ContinentAdapter(List<String> items, OnSelect listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_continent, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        String name = items.get(pos);
        h.name.setText(name);
        boolean isSel = pos == selectedPos;
        h.check.setVisibility(isSel ? View.VISIBLE : View.GONE);
        h.card.setBackgroundResource(isSel
                ? R.drawable.bg_card_selected : R.drawable.bg_card);
        h.itemView.setOnClickListener(v -> {
            int old = selectedPos;
            selectedPos = h.getAdapterPosition();
            notifyItemChanged(old);
            notifyItemChanged(selectedPos);
            listener.onSelect(name);
        });
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        View card; ImageView check; TextView name;
        VH(View v) {
            super(v);
            card = v.findViewById(R.id.continentCard);
            check = v.findViewById(R.id.continentCheck);
            name = v.findViewById(R.id.continentName);
        }
    }
}