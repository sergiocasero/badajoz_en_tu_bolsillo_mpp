package com.badajoz.badajozentubolsillo.android.utils

import android.content.Context
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import coil.request.ImageRequest
import com.badajoz.flow.CStateFlow
import com.badajoz.badajozentubolsillo.utils.BASE_URL
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

@Composable
fun <T> CStateFlow<T>.withLifeCycle(): State<T> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val flow = remember(this, lifecycleOwner) {
        this.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return flow.collectAsState(this.value)
}

@Composable
fun <T> CStateFlow<List<T>>.withLifeCycle2(): State<List<T>> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val flow = remember(this, lifecycleOwner) {
        this.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return flow.collectAsState(this.value)
}

fun basicAuth(user: String, pass: String): String {
    return "Basic " + Base64.encodeToString(
        "$user:$pass".toByteArray(),
        Base64.NO_WRAP
    )
}

fun String.staticUrl(user: String, pass: String, context: Context): ImageRequest {
    return ImageRequest.Builder(context)
        .data("$BASE_URL/$this")
        .addHeader("Authorization", basicAuth(user, pass))
        .crossfade(true)
        .build()
}

val defaultCardElevation = 8.dp