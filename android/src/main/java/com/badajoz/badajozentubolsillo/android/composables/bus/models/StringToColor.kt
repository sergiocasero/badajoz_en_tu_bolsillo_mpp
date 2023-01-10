package com.badajoz.badajozentubolsillo.android.composables.bus.models

import androidx.compose.ui.graphics.Color

fun String.toColor() =
    try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        androidx.compose.ui.graphics.Color.White
    }