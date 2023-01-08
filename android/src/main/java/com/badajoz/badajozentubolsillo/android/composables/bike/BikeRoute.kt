package com.badajoz.badajozentubolsillo.android.composables.bike


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation
import com.badajoz.badajozentubolsillo.viewmodel.BikeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BikeState
import com.badajoz.badajozentubolsillo.viewmodel.BikeViewModel

@Composable
fun BikeRoute() {
    val viewModel = remember { BikeViewModel(initialState = BikeState.InProgress) }

    BikeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun BikeContent(state: BikeState, onEvent: (BikeEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(BikeEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is BikeState.InProgress -> LoadingView()
                is BikeState.Error -> TODO()
                is BikeState.Success -> BikeSuccess(state.bikeStations)
            }
        }
    )
}

@Composable
fun BikeSuccess(bikeStations: List<BikeStation>) {
    LazyColumn {
        items(bikeStations) { bikeStation ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = bikeStation.name)
            }
        }
    }
}