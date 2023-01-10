package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.badajoz.badajozentubolsillo.android.composables.bus.components.BusHomeContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent

@Composable
fun BusHomeRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { BusHomeViewModel(initialState = BusHomeState.InProgress) }

    BusHomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}
