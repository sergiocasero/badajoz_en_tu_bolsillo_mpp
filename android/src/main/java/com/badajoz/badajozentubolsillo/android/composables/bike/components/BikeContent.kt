package com.badajoz.badajozentubolsillo.android.composables.bike.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.badajoz.badajozentubolsillo.android.composables.bike.models.toMarker
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.reusable.MapWithMarkers
import com.badajoz.badajozentubolsillo.viewmodel.BikeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BikeState
import com.badajoz.badajozentubolsillo.viewmodel.BikeViewType
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun BikeContent(state: BikeState, onEvent: (BikeEvent) -> Unit, onNavigate: (Screen) -> Unit = {}) {
    LaunchedEffect(Unit) {
        onEvent(BikeEvent.Attach)
    }

    Scaffold(
        bottomBar = {
            if (state !is BikeState.Error) {
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
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (state) {
                is BikeState.InProgress -> LoadingView()
                is BikeState.Error -> ErrorView(error = state.error) { onEvent(BikeEvent.Attach) }
                is BikeState.Success -> when (state.view) {
                    BikeViewType.List -> BikeList(state.bikeStations) {
                        onNavigate(Screen.MapLink("${it.lat},${it.lng}"))
                    }

                    BikeViewType.Map -> MapWithMarkers(
                        markers = state.bikeStations.map { it.toMarker() },
                    )
                }
            }
        }
    }
}