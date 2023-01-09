package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeState
import com.badajoz.badajozentubolsillo.viewmodel.BusHomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent

@Composable
fun BusHomeRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { BusHomeViewModel(initialState = BusHomeState.InProgress) }

    BusHomeContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun BusHomeContent(state: BusHomeState, onEvent: (BusHomeEvent) -> Unit, onNavigationEvent: (NavigationEvent) -> Unit) {
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
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                is BusHomeState.InProgress -> LoadingView()
                is BusHomeState.BusLines -> BusLinesView(state.lines) {
                    onNavigationEvent(
                        NavigationEvent.OnBusLineDetail(it.id)
                    )
                } // BusLinesView
                // (lines =
                // state.lines)
                is BusHomeState.FavoriteStops -> TODO() // BusStopsView(stops = state.stops)
                is BusHomeState.Error -> TODO() // ErrorView(error = state.error)
            }
        }

    }
}

@Composable
fun BusLinesView(lines: List<BusLine>, onLineClick: (BusLine) -> Unit) {
    LazyColumn {
        items(lines) { busLine ->
            BusLineItemView(line = busLine)
        }
    }
}

@Composable
fun BusLineItemView(line: BusLine) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .background(line.color.toColor())
                    .fillMaxHeight()
            ) {
                Text(
                    text = line.name,
                    modifier = Modifier
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = line.description,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                style = MaterialTheme.typography.body1
            )

            AsyncImage(
                model = line.image.staticUrl(LocalContext.current),
                contentDescription = line.more,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

fun String.toColor() =
    try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        Color.White
    }

@Preview
@Composable
fun BusLinesViewPreview() {
    BusHomeContent(
        state = BusHomeState.BusLines(
            listOf(
                BusLine(
                    code = 71,
                    description = "LM1 Barriada de Llera -Estación FF . CC .",
                    id = 55,
                    more = "Barriada de Llera - Estación FF . CC .",
                    name = "LM1",
                    image = "static/tubasa/images/LM1.jpg",
                    color = "FFf5c9ff",
                ),
                BusLine(
                    code = 73,
                    description = "LM3 La Pilara - Urb.Deh.Calamón",
                    id = 54,
                    more = "La Pilara -Urb.Deh.Calamón",
                    name = "LM3",
                    image = "static/tubasa/images/LM3.jpg",
                    color = "FFffffff",
                ),
                BusLine(
                    code = 74,
                    description = "LM4 Alvarado -Tres Arroyos -Avenida de Huelva",
                    id = 57,
                    more = "Alvarado - Avd.Huelva",
                    name = "LM4",
                    image = "static/tubasa/images/LM4.jpg",
                    color = "FFf9e2ff"
                ),
                BusLine(
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
