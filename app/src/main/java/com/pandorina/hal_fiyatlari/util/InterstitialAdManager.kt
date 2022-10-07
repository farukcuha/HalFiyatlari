package com.pandorina.hal_fiyatlari.util

import androidx.activity.ComponentActivity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.pandorina.hal_fiyatlari.HalFiyatlariApp

object InterstitialAdManager {
    const val TAG = "InterstitialAdManager"
    private var mInterstitialAd = MutableLiveData<InterstitialAd?>(null)
    var mActivity: ComponentActivity? = null
    private val adRequest = AdRequest.Builder().build()

    fun initialize(activity: ComponentActivity){
        mActivity = activity
        MobileAds.initialize(activity) {}
        mInterstitialAd.observe(activity){
            if (it == null) fetchAd()
        }
    }

    private fun fetchAd(){
        InterstitialAd.load(HalFiyatlariApp.get(), "ca-app-pub-3940256099942544/1033173712",
            adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.toString())
                mInterstitialAd.postValue(null)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd.postValue(interstitialAd)
            }
        })
    }

    fun show(){
        mActivity?.let {
            mInterstitialAd.value?.show(it)
            mInterstitialAd.postValue(null)
            Log.d(TAG, "Ad was showed.")
        }
    }
}