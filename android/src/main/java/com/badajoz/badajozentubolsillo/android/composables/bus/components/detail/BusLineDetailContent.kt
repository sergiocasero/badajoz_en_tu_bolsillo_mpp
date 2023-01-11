package com.badajoz.badajozentubolsillo.android.composables.bus.components.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.badajoz.badajozentubolsillo.android.composables.bus.components.BusTimesDialog
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailState
import com.badajoz.badajozentubolsillo.viewmodel.Destination

@Composable
fun BusLineDetailContent(
    state: BusLineDetailState,
    stops: List<BusStop>,
    bigImage: Boolean,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(BusLineDetailEvent.Attach)
    }

    when (state) {
        is BusLineDetailState.InProgress -> LoadingView(background = Color.Transparent)
        is BusLineDetailState.Error -> ErrorView(error = state.error) { onEvent(BusLineDetailEvent.Attach) }
        is BusLineDetailState.Success -> {
            val selectedStop = state.selectedStop
            if (selectedStop != null) {
                BusTimesDialog(selectedStop) { onEvent(BusLineDetailEvent.OnDismissDialogClick) }
            }
            BusLineDetailView(
                title = state.title,
                imageRoute = state.imageRoute,
                stops = stops,
                bigImage = bigImage,
                onEvent = onEvent,
                onNavigate = onNavigate
            )
        }
    }
}