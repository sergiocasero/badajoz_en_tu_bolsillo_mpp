package com.badajoz.badajozentubolsillo.android.composables.bus.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                AlertDialog(
                    title = { Text(text = selectedStop.stop.name) },
                    text = {
                        Column {
                            selectedStop.times.forEach {
                                Row(
                                    modifier = Modifier.padding(4.dp)
                                ) {
                                    Text(
                                        text = it.line,
                                        modifier = Modifier.width(30.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "Próximo a las ${it.time}")
                                }
                            }
                        }
                    },
                    onDismissRequest = { onEvent(BusLineDetailEvent.OnDismissDialogClick) },
                    buttons = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(8.dp),
                                onClick = { onEvent(BusLineDetailEvent.OnDismissDialogClick) }
                            ) {
                                Text(text = "Cerrar")
                            }
                        }
                    }
                )
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