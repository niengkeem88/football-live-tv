package com.footballlive.tv.ui.livescore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.footballlive.tv.R;
import com.footballlive.tv.data.remote.RetrofitClient;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureItem;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureResponse;
import com.footballlive.tv.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveScoreFragment extends Fragment {
    private SwipeRefreshLayout refresh;
    private RecyclerView rv;
    private TextView empty;
    private FixtureAdapter adapter;
    private final List<FixtureItem> data = new ArrayList<>();

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle s) {
        return inflater.inflate(R.layout.fragment_live_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle s) {
        refresh = view.findViewById(R.id.lsRefresh);
        rv = view.findViewById(R.id.lsList);
        empty = view.findViewById(R.id.lsEmpty);

        adapter = new FixtureAdapter(data);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        refresh.setOnRefreshListener(this::load);
        load();
    }

    private void load() {
        if (getContext() == null) return;
        if (!NetworkUtils.isOnline(getContext())) {
            refresh.setRefreshing(false);
            showEmpty(getString(R.string.no_internet));
            return;
        }
        refresh.setRefreshing(true);
        RetrofitClient.get().getLiveFixtures("all").enqueue(new Callback<FixtureResponse>() {
            @Override
            public void onResponse(@NonNull Call<FixtureResponse> call,
                                   @NonNull Response<FixtureResponse> resp) {
                refresh.setRefreshing(false);
                data.clear();
                if (resp.body() != null && resp.body().response != null)
                    data.addAll(resp.body().response);
                adapter.notifyDataSetChanged();
                if (data.isEmpty()) showEmpty("No live matches right now");
                else hideEmpty();
            }
            @Override
            public void onFailure(@NonNull Call<FixtureResponse> call, @NonNull Throwable t) {
                refresh.setRefreshing(false);
                showEmpty("Failed to load. Pull to retry.");
            }
        });
    }

    private void showEmpty(String msg) {
        empty.setText(msg);
        empty.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
    }

    private void hideEmpty() {
        empty.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
    }
}