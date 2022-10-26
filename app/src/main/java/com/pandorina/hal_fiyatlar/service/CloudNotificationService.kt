package com.pandorina.hal_fiyatlar.service

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pandorina.hal_fiyatlar.util.Notification.notifyPrices


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class CloudNotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification?.title
        val text = message.notification?.body
        notifyPrices(this, "cloud_message", title, text)
        super.onMessageReceived(message)
    }
}