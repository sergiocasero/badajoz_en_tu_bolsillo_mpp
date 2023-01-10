package com.badajoz.badajozentubolsillo.android.composables.bus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.bus.components.detail.BusLineDetailContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.android.utils.withLifeCycle
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailState
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun BusLineDetailRoute(lineId: Int, onNavigate: (Screen) -> Unit) {
    val viewModel = remember { BusLineDetailViewModel(lineId = lineId, initialState = BusLineDetailState.InProgress) }

    BusLineDetailContent(
        state = viewModel.stateWithLifecycle().value,
        stops = viewModel.busStopsState.withLifeCycle().value,
        bigImage = viewModel.bigImageState.withLifeCycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}

















