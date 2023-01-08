package com.badajoz.badajozentubolsillo.android.composables.bike


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.MapWithMarkers
import com.badajoz.badajozentubolsillo.android.composables.Marker
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation
import com.badajoz.badajozentubolsillo.viewmodel.BikeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BikeState
import com.badajoz.badajozentubolsillo.viewmodel.BikeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.BikeViewType

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
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = state is BikeState.Success && state.view == BikeViewType.Map,
                    onClick = { onEvent(BikeEvent.OnBikeMapClick) },
                    icon = {
                        Icon(Icons.Default.Map, contentDescription = "Mapa BiBa")
                    },
                    label = { Text("Mapa") }
                )
                BottomNavigationItem(
                    selected = state is BikeState.Success && state.view == BikeViewType.List,
                    onClick = { onEvent(BikeEvent.OnBikeListClick) },
                    icon = {
                        Icon(Icons.Default.List, contentDescription = "Listado BiBa")
                    },
                    label = { Text("Listado") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (state) {
                is BikeState.InProgress -> LoadingView()
                is BikeState.Error -> TODO()
                is BikeState.Success -> when (state.view) {
                    BikeViewType.List -> BikeList(state.bikeStations)
                    BikeViewType.Map -> MapWithMarkers(
                        markers = state.bikeStations.map { it.toMarker() },
                    )
                }
            }
        }
    }
}

@Composable
fun BikeList(bikeStations: List<BikeStation>) {
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

private fun BikeStation.toMarker() = Marker(
    title = name,
    latitude = lat,
    longitude = lng
)