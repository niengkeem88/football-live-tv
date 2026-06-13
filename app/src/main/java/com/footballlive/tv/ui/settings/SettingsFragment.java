package com.footballlive.tv.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.footballlive.tv.R;
import com.footballlive.tv.data.local.Prefs;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle s) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle s) {
        Prefs prefs = new Prefs(requireContext());

        MaterialSwitch push = view.findViewById(R.id.swPush);
        MaterialSwitch dark = view.findViewById(R.id.swDark);
        MaterialSwitch auto = view.findViewById(R.id.swAuto);
        MaterialSwitch saver = view.findViewById(R.id.swDataSaver);

        push.setChecked(prefs.isPushEnabled());
        dark.setChecked(prefs.isDarkMode());
        auto.setChecked(prefs.isAutoRefresh());
        saver.setChecked(prefs.isDataSaver());

        push.setOnCheckedChangeListener((b, c) -> prefs.setPushEnabled(c));
        auto.setOnCheckedChangeListener((b, c) -> prefs.setAutoRefresh(c));
        saver.setOnCheckedChangeListener((b, c) -> prefs.setDataSaver(c));
        dark.setOnCheckedChangeListener((b, c) -> {
            prefs.setDarkMode(c);
            AppCompatDelegate.setDefaultNightMode(c
                    ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });
    }
}