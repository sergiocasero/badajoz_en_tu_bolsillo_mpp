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
import androidx.compose.ui.res.stringResource
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.bike.models.toMarker
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.reusable.MapWithMarkers
import com.badajoz.badajozentubolsillo.viewmodel.bike.BikeEvent
import com.badajoz.badajozentubolsillo.viewmodel.bike.BikeState
import com.badajoz.badajozentubolsillo.viewmodel.bike.BikeViewType
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun BikeContent(state: BikeState, onEvent: (BikeEvent) -> Unit, onNavigate: (Destination) -> Unit = {}) {
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
                            Icon(Icons.Default.Map, contentDescription = stringResource(id = R.string.map))
                        },
                        label = { Text(stringResource(id = R.string.map)) }
                    )
                    BottomNavigationItem(
                        selected = state is BikeState.Success && state.view == BikeViewType.List,
                        onClick = { onEvent(BikeEvent.OnBikeListClick) },
                        icon = {
                            Icon(Icons.Default.List, contentDescription = stringResource(id = R.string.list))
                        },
                        label = { Text(stringResource(id = R.string.list)) }
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
                    BikeViewType.List -> BikeList(state.appConfigData, state.bikeStations) {
                        onNavigate(
                            Screen.MapLink.toDestination(
                                "${it.lat},${it.lng}" // TODO Move to string
                            )
                        )
                    }

                    BikeViewType.Map -> MapWithMarkers(
                        markers = state.bikeStations.map { it.toMarker() },
                    )
                }
            }
        }
    }
}