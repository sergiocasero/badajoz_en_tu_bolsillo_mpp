package com.badajoz.badajozentubolsillo.analytics

import android.os.Bundle
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

actual class SharedAnalytics : Analytics {

    private val firebaseAnalytics by lazy { Firebase.analytics }

    override fun logEvent(screen: Screen, params: Map<String, Any?>?) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screen.route)

            params?.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Double -> putDouble(key, value)
                    is Boolean -> putBoolean(key, value)
                }
            }
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}