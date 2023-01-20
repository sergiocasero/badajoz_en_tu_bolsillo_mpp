package com.badajoz.badajozentubolsillo.android

import com.google.firebase.messaging.FirebaseMessagingService

class BadajozFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        println("New FCM token: $token")
        super.onNewToken(token)
    }
}

