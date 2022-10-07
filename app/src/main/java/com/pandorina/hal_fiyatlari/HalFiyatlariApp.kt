package com.pandorina.hal_fiyatlari

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.hilt.android.HiltAndroidApp

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
        app = this
    }
}