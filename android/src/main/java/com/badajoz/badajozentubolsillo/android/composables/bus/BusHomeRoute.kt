package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.bus.components.home.BusHomeContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.bus.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.bus.BusHomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Destination

@Composable
fun BusHomeRoute(onNavigate: (Destination) -> Unit) {
    val viewModel = remember { BusHomeViewModel(initialState = BusHomeState.InProgress) }

    BusHomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
