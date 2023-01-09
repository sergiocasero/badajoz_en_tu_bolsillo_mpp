package com.badajoz.badajozentubolsillo.android.utils

import android.content.Context
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import coil.request.ImageRequest
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BASIC_AUTH_PASSWORD
import com.badajoz.badajozentubolsillo.utils.BASIC_AUTH_USER
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState

@Composable
fun <S : ViewState, E> RootViewModel<S, E>.stateWithLifecycle(): State<S> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val flow = remember(state, lifecycleOwner) {
        state.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return flow.collectAsState(state.value)
}

fun basicAuth(): String {
    return "Basic " + Base64.encodeToString(
        "$BASIC_AUTH_USER:$BASIC_AUTH_PASSWORD".toByteArray(),
        Base64.NO_WRAP
    )
}

fun String.staticUrl(context: Context): ImageRequest {
    return ImageRequest.Builder(context)
        .data("$BASE_URL/$this")
        .addHeader("Authorization", basicAuth())
        .crossfade(true)
        .build()
}