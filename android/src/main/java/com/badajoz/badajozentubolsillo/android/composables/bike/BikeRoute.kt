package com.badajoz.badajozentubolsillo.android.composables.bike


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.bike.components.BikeContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.BikeState
import com.badajoz.badajozentubolsillo.viewmodel.BikeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun BikeRoute(onNavigate: (Destination) -> Unit) {
    val viewModel = remember { BikeViewModel(initialState = BikeState.InProgress) }

    BikeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
