package com.badajoz.badajozentubolsillo.android.composables.splash


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.BuildConfig
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.splash.SplashState
import com.badajoz.badajozentubolsillo.viewmodel.splash.SplashViewModel

@Composable
fun SplashRoute(onNavigate: (Destination) -> Unit) {
    val viewModel = remember {
        SplashViewModel(
            currentAppVersion = BuildConfig.VERSION_CODE,
            initialState = SplashState.InProgress
        )
    }

    SplashContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}

