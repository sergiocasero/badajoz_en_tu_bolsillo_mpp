package com.badajoz.badajozentubolsillo.analytics

import com.badajoz.badajozentubolsillo.viewmodel.Screen

actual class SharedAnalytics : Analytics {
    override fun logEvent(screen: Screen, params: Map<String, Any?>?) {
        println(screen)
    }
}