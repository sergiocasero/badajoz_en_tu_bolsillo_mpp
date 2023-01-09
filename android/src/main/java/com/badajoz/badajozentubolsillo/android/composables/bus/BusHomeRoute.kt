package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeViewModel

@Composable
fun BusHomeRoute() {
    val viewModel = remember { BusHomeViewModel(initialState = BusHomeState.InProgress) }

    BusHomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun BusHomeContent(state: BusHomeState, onEvent: (BusHomeEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(BusHomeEvent.Attach)
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = state is BusHomeState.BusLines,
                    onClick = { onEvent(BusHomeEvent.OnBusLinesClick) },
                    icon = {
                        Icon(Icons.Default.List, contentDescription = "Líneas de buses")
                    },
                    label = { Text("Líneas") }
                )
                BottomNavigationItem(
                    selected = state is BusHomeState.FavoriteStops,
                    onClick = { onEvent(BusHomeEvent.OnFavoriteStopsClick) },
                    icon = {
                        Icon(Icons.Default.Favorite, contentDescription = "Paradas favoritas")
                    },
                    label = { Text("Paradas favoritas") }
                )
            }
        }
    ) {
        when (state) {
            is BusHomeState.InProgress -> LoadingView()
            is BusHomeState.BusLines -> BusLinesView(state.lines)// BusLinesView(lines = state.lines)
            is BusHomeState.FavoriteStops -> TODO() // BusStopsView(stops = state.stops)
            is BusHomeState.Error -> TODO() // ErrorView(error = state.error)
        }
    }
}

@Composable
fun BusLinesView(lines: List<BusLine>) {
    LazyColumn {
        items(lines) { busLine ->
            BusLineItemView(line = busLine)
        }
    }
}

@Composable
fun BusLineItemView(line: BusLine) {
    Text(line.name)
}
