package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.FmdEvent
import com.badajoz.badajozentubolsillo.viewmodel.FmdState

@Composable
fun FmdContent(state: FmdState, onEvent: (FmdEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(FmdEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is FmdState.InProgress -> LoadingView()
                is FmdState.Error -> TODO()
                FmdState.NotLoggedIn -> LoginView(onEvent)
                is FmdState.SportList -> TODO()
            }
        }
    )
}
