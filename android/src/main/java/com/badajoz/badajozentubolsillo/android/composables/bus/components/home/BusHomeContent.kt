package com.badajoz.badajozentubolsillo.android.composables.bus.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.android.composables.bus.components.home.FavoriteStopsView
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent

@Composable
fun BusHomeContent(
    state: BusHomeState,
    onEvent: (BusHomeEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(BusHomeEvent.Attach)
    }

    Scaffold(
        bottomBar = {
            if (state !is BusHomeState.Error) {
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
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                is BusHomeState.InProgress -> LoadingView()
                is BusHomeState.BusLines -> BusLinesView(state.lines) {
                    onNavigationEvent(NavigationEvent.OnBusLineDetail(it.id))
                }

                is BusHomeState.FavoriteStops -> FavoriteStopsView(state.stops) {
                    onEvent(BusHomeEvent.OnRemoveStopClick(it))
                }

                is BusHomeState.Error -> ErrorView(error = state.error) { onEvent(BusHomeEvent.Attach) }
            }
        }
    }
}

@Preview
@Composable
fun BusLinesViewPreview() {
    BusHomeContent(
        state = BusHomeState.BusLines(
            listOf(
                BusLineItem(
                    code = 71,
                    description = "LM1 Barriada de Llera -Estación FF . CC .",
                    id = 55,
                    more = "Barriada de Llera - Estación FF . CC .",
                    name = "LM1",
                    image = "static/tubasa/images/LM1.jpg",
                    color = "FFf5c9ff",
                ),
                BusLineItem(
                    code = 73,
                    description = "LM3 La Pilara - Urb.Deh.Calamón",
                    id = 54,
                    more = "La Pilara -Urb.Deh.Calamón",
                    name = "LM3",
                    image = "static/tubasa/images/LM3.jpg",
                    color = "FFffffff",
                ),
                BusLineItem(
                    code = 74,
                    description = "LM4 Alvarado -Tres Arroyos -Avenida de Huelva",
                    id = 57,
                    more = "Alvarado - Avd.Huelva",
                    name = "LM4",
                    image = "static/tubasa/images/LM4.jpg",
                    color = "FFf9e2ff"
                ),
                BusLineItem(
                    code = 10,
                    description = "LCM Plaza de La Libertad - Cementerio Nuevo",
                    id = 53,
                    more = "Plz.de la Libertad - Cementerio",
                    name = "LCM",
                    image = "static/tubasa/images/LCM.jpg",
                    color = "FF3542ff"
                )
            )
        ),
        onEvent = {},
        onNavigationEvent = {}
    )
}
