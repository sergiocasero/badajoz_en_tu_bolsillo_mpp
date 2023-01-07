package com.badajoz.badajozentubolsillo.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState

@Composable
fun <S : ViewState, E, A> RootViewModel<S, E, A>.stateWithLifecycle(): State<S> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val flow = remember(state, lifecycleOwner) {
        state.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    return flow.collectAsState(state.value)
}

val <T> T.exhaustive: T
    get() = this