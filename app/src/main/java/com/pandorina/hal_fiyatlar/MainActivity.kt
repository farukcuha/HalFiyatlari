package com.pandorina.hal_fiyatlar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.pandorina.hal_fiyatlar.presentation.navigation.MainNavController
import com.pandorina.hal_fiyatlar.presentation.theme.KotlinDslExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.play.core.install.model.ActivityResult
import com.pandorina.hal_fiyatlar.util.AppUpdateManager
import com.pandorina.hal_fiyatlar.util.InterstitialAdManager
import com.pandorina.hal_fiyatlar.util.in_app_review.GNTReviewManager
import com.pandorina.hal_fiyatlar.util.showToast

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinDslExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainNavController(navController = rememberNavController())
                }
            }
        }

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("remote_config", "fetched")
            } else Log.d("remote_config", "fetching is failed")
        }

        AppUpdateManager().checkUpdateInfo(this)
        InterstitialAdManager(this)
        GNTReviewManager.with(this)
            .setInstallDays(2)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .setDebug(false)
            .monitor()

        GNTReviewManager.showRateDialogIfMeetsConditions(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppUpdateManager.UPDATE_REQUEST) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    applicationContext.showToast("ok")
                }
                Activity.RESULT_CANCELED -> {
                    applicationContext.showToast("canceled")
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    applicationContext.showToast("failed")
                }
            }
        }
    }

}