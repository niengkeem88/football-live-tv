package com.footballlive.tv.ui.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.footballlive.tv.R;
import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.VH> {
    private final List<Slide> slides;
    public OnboardingAdapter(List<Slide> slides) { this.slides = slides; }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_onboarding_slide, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Slide s = slides.get(pos);
        h.image.setImageResource(s.imageRes);
        h.title.setText(s.title);
        h.subtitle.setText(s.subtitle);
    }

    @Override public int getItemCount() { return slides.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image; TextView title, subtitle;
        VH(View v) {
            super(v);
            image = v.findViewById(R.id.slideImage);
            title = v.findViewById(R.id.slideTitle);
            subtitle = v.findViewById(R.id.slideSubtitle);
        }
    }
}