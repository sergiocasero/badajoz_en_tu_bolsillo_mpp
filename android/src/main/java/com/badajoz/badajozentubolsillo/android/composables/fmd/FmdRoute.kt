package com.badajoz.badajozentubolsillo.android.composables.fmd


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerlist.FmdContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.FmdState
import com.badajoz.badajozentubolsillo.viewmodel.FmdViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdRoute(onNavigate: (Destination) -> Unit) {
    val viewModel = remember { FmdViewModel(initialState = FmdState.InProgress) }

    FmdContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate,
    )
}
