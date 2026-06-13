package com.footballlive.tv.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String FILE = "flt_prefs";
    private final SharedPreferences sp;

    public Prefs(Context ctx) {
        sp = ctx.getApplicationContext().getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    public boolean isFirstLaunch() { return sp.getBoolean("isFirstLaunch", true); }
    public void setFirstLaunch(boolean v) { sp.edit().putBoolean("isFirstLaunch", v).apply(); }

    public String getContinent() { return sp.getString("selectedContinent", ""); }
    public void setContinent(String v) { sp.edit().putString("selectedContinent", v).apply(); }

    public boolean isTermsAccepted() { return sp.getBoolean("termsAccepted", false); }
    public void setTermsAccepted(boolean v) { sp.edit().putBoolean("termsAccepted", v).apply(); }

    public boolean isPushEnabled() { return sp.getBoolean("pushNotifications", true); }
    public void setPushEnabled(boolean v) { sp.edit().putBoolean("pushNotifications", v).apply(); }

    public boolean isDarkMode() { return sp.getBoolean("darkMode", false); }
    public void setDarkMode(boolean v) { sp.edit().putBoolean("darkMode", v).apply(); }

    public boolean isAutoRefresh() { return sp.getBoolean("autoRefresh", true); }
    public void setAutoRefresh(boolean v) { sp.edit().putBoolean("autoRefresh", v).apply(); }

    public boolean isDataSaver() { return sp.getBoolean("dataSaver", false); }
    public void setDataSaver(boolean v) { sp.edit().putBoolean("dataSaver", v).apply(); }
}