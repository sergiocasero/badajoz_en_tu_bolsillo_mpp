package com.badajoz.badajozentubolsillo.analytics

import com.badajoz.badajozentubolsillo.viewmodel.Screen

interface Analytics {
    fun logEvent(screen: Screen, params: Map<String, Any?>? = null)
}

expect class SharedAnalytics() : Analytics