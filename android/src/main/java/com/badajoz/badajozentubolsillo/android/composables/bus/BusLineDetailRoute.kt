package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.TopBar
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.android.utils.withLifeCycle
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailState
import com.badajoz.badajozentubolsillo.viewmodel.BusLineDetailViewModel
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent

@Composable
fun BusLineDetailRoute(lineId: Int, onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { BusLineDetailViewModel(lineId = lineId, initialState = BusLineDetailState.InProgress) }

    BusLineDetailContent(
        state = viewModel.stateWithLifecycle().value,
        stops = viewModel.busStopsState.withLifeCycle().value,
        bigImage = viewModel.bigImageState.withLifeCycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun BusLineDetailContent(
    state: BusLineDetailState,
    stops: List<BusStop>,
    bigImage: Boolean,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(BusLineDetailEvent.Attach)
    }

    when (state) {
        is BusLineDetailState.InProgress -> LoadingView(background = Color.Transparent)
        is BusLineDetailState.Error -> TODO()
        is BusLineDetailState.Success -> BusLineDetailView(
            title = state.title,
            imageRoute = state.imageRoute,
            stops = stops,
            bigImage = bigImage,
            onEvent = onEvent,
            onNavigationEvent = onNavigationEvent
        )
    }
}

@Composable
fun BusLineDetailView(
    title: String,
    imageRoute: String,
    bigImage: Boolean,
    stops: List<BusStop>,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = title, icon = Icons.Default.ArrowBack) {
                onNavigationEvent(NavigationEvent.OnBack(MenuState.Bus))
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onEvent(BusLineDetailEvent.OnImageClick) },
                modifier = Modifier.padding(16.dp),
                text = { Text(if (bigImage) "Ver paradas" else "Ver recorrido") },
                icon = {
                    Icon(
                        if (bigImage) Icons.Default.List else Icons.Default.Search,
                        contentDescription = "Ampliar recorrido"
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            if (bigImage) {
                AsyncImage(
                    model = imageRoute.staticUrl(LocalContext.current),
                    contentDescription = "Recorrido ${title}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 80.dp)
                        .fillMaxWidth(),
                )
            } else {
                LazyColumn {
                    items(stops) { stop ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            elevation = defaultCardElevation
                        ) {
                            Row {
                                Text(
                                    stop.name, modifier = Modifier
                                        .padding(16.dp)
                                        .weight(1f),
                                    style = MaterialTheme.typography.body1
                                )
                                IconButton(onClick = {
                                    onEvent(
                                        BusLineDetailEvent.OnFavoriteClick(stop.copy(favorite = !stop.favorite))
                                    )
                                }) {
                                    Icon(
                                        if (stop.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = "Marcar como favorita"
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BusLineDetailPreview() {
    BusLineDetailView(
        title = "L2 Dragones Hernan Cortes- Estacion Ferrocarriles - Gurugu",
        bigImage = false,
        imageRoute = "static/tubasa/images/L2.jpg",
        stops = listOf(
            BusStop(id = 200, line = 3, name = "Calle Pedro de Valdivia  3"),
            BusStop(id = 201, line = 3, name = "Ronda del Pilar  4"),
            BusStop(id = 212, line = 3, name = "Av. de Huelva  10"),
            BusStop(id = 213, line = 3, name = "Av. Villanueva  24"),
            BusStop(id = 224, line = 3, name = "Av. Antonio Masa Campos 55"),
            BusStop(id = 225, line = 3, name = "Av. Antonio Masa Campos  7"),
            BusStop(id = 226, line = 3, name = "Av. Antonio Masa Campos 1"),
            BusStop(id = 53, line = 3, name = "Av. Adolfo Díaz Ambrona  88"),
            BusStop(id = 54, line = 3, name = "Av. Carolina Coronado  4"),
            BusStop(id = 55, line = 3, name = "Av. Carolina Coronado  16"),
        ),
        onEvent = {},
        onNavigationEvent = {}
    )
}













