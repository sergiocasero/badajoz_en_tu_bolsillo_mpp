package com.badajoz.badajozentubolsillo.android.composables.fmd.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.FmdEvent
import com.badajoz.badajozentubolsillo.viewmodel.FmdState
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdContent(state: FmdState, onEvent: (FmdEvent) -> Unit, onNavigate: (Screen) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(FmdEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is FmdState.InProgress -> LoadingView()
                is FmdState.Error -> ErrorView(error = state.error) { onEvent(FmdEvent.Attach) }
                FmdState.NotLoggedIn -> LoginView(onEvent)
                is FmdState.CenterList -> FmdCentersList(
                    centers = state.centers,
                    onNavigate = { onNavigate(it) },
                )
            }
        }
    )
}
