package com.badajoz.badajozentubolsillo.android.composables.fmd


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.fmd.components.centerdetail.FmdCenterDetailContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.fmd.FmdCenterDetailState
import com.badajoz.badajozentubolsillo.viewmodel.fmd.FmdCenterDetailViewModel

@Composable
fun FmdCenterDetailRoute(id: Int, onNavigate: (Destination) -> Unit) {
    val viewModel = remember { FmdCenterDetailViewModel(id, initialState = FmdCenterDetailState.InProgress) }

    FmdCenterDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}

