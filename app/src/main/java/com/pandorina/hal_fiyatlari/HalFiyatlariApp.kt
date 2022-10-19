package com.pandorina.hal_fiyatlari

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.hilt.android.HiltAndroidApp
import papaya.`in`.admobopenads.AppOpenManager

@HiltAndroidApp
class HalFiyatlariApp: Application(){

    companion object {
        lateinit var app: HalFiyatlariApp

        fun get(): HalFiyatlariApp{
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this){}
        AppOpenManager(this, BuildConfig.APP_OPEN_AD_UNIT_ID)
        app = this
    }
}