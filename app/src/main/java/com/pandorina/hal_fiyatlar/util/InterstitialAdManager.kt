package com.pandorina.hal_fiyatlar.util

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.pandorina.hal_fiyatlar.BuildConfig

class InterstitialAdManager(
    private val activity: Activity
) {

    companion object {
        const val TAG = "InterstitialAdManager"
        val adRequest = AdRequest.Builder().build()
    }


    init {
        InterstitialAd.load(activity, BuildConfig.INTERSTITIAL_AD_UNIT_ID,
            adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd.show(activity)
                }
            })
    }
}