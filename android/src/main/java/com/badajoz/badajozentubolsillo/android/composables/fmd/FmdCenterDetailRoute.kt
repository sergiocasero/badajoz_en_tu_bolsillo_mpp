package com.badajoz.badajozentubolsillo.android.composables.fmd


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail.FmdCenterDetailContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.FmdCenterDetailState
import com.badajoz.badajozentubolsillo.viewmodel.FmdCenterDetailViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun FmdCenterDetailRoute(id: Int, onNavigate: (Destination) -> Unit) {
    val viewModel = remember { FmdCenterDetailViewModel(id, initialState = FmdCenterDetailState.InProgress) }

    FmdCenterDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}

