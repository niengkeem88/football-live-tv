package com.footballlive.tv.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public final class AdManager {
    // TEST IDs — replace with production IDs before release
    public static final String BANNER_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final String INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712";

    private static InterstitialAd interstitial;

    private AdManager() {}

    public static void loadBanner(Context ctx, ViewGroup container) {
        AdView adView = new AdView(ctx);
        adView.setAdUnitId(BANNER_ID);
        adView.setAdSize(AdSize.BANNER);
        container.removeAllViews();
        container.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    public static void preloadInterstitial(Context ctx) {
        InterstitialAd.load(ctx, INTERSTITIAL_ID, new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override public void onAdLoaded(InterstitialAd ad) { interstitial = ad; }
                    @Override public void onAdFailedToLoad(LoadAdError e) { interstitial = null; }
                });
    }

    public static void showInterstitial(Activity activity) {
        if (interstitial != null) {
            interstitial.show(activity);
            interstitial = null;
            preloadInterstitial(activity);
        }
    }
}