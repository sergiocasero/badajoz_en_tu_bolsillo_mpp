package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.bus.components.BusHomeContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun BusHomeRoute(onNavigate: (Screen) -> Unit) {
    val viewModel = remember { BusHomeViewModel(initialState = BusHomeState.InProgress) }

    BusHomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
