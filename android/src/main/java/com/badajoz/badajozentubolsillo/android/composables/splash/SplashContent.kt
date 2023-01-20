package com.badajoz.badajozentubolsillo.android.composables.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.badajoz.badajozentubolsillo.viewmodel.splash.SplashEvent
import com.badajoz.badajozentubolsillo.viewmodel.splash.SplashState

@Composable
fun SplashContent(state: SplashState, onEvent: (SplashEvent) -> Unit, onNavigate: (Destination) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(SplashEvent.Attach)
    }

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            when (state) {
                is SplashState.InProgress -> LoadingView()
                is SplashState.Error -> ErrorView(error = state.error) { onEvent(SplashEvent.Attach) }
                SplashState.NoUpdateNeeded -> LaunchedEffect("") {
                    onNavigate(Screen.Menu.News.toDestination())
                }

                SplashState.UpdateNeeded -> UpdateNeededView { onNavigate(it) }
            }
        }
    }
}