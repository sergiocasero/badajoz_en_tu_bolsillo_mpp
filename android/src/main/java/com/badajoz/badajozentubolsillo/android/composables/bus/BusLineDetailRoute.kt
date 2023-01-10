package com.badajoz.badajozentubolsillo.android.composables.bus


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineDetail
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
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun BusLineDetailContent(
    state: BusLineDetailState,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(BusLineDetailEvent.Attach)
    }

    when (state) {
        is BusLineDetailState.InProgress -> LoadingView()
        is BusLineDetailState.Error -> TODO()
        is BusLineDetailState.Success -> BusLineDetailView(
            state.line,
            state.bigImage,
            onEvent = onEvent,
            onNavigationEvent = onNavigationEvent
        )
    }
}

@Composable
fun BusLineDetailView(
    line: BusLineDetail,
    bigImage: Boolean,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = line.description, icon = Icons.Default.ArrowBack) {
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
                    model = line.image.staticUrl(LocalContext.current),
                    contentDescription = "Recorrido ${line.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 80.dp)
                        .fillMaxWidth(),
                )
            } else {
                LazyColumn {
                    items(line.stops) { stop ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = defaultCardElevation
                        ) {
                            Text(
                                stop.name, modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.body1
                            )
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
        line = BusLineDetail(
            code = 2,
            description = "L2 Dragones Hernan Cortes- Estacion Ferrocarriles - Gurugu",
            id = 3,
            more = "Drag. H. Cortés - Gururgú",
            name = "L2",
            image = "static/tubasa/images/L2.jpg",
            color = "#a09c9c",
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
                BusStop(id = 56, line = 3, name = "Av. Carolina Coronado"),
                BusStop(id = 57, line = 3, name = "Argüello Carvajal Teologo Siglo XVII  23"),
                BusStop(id = 58, line = 3, name = "Cardenal Cisneros  48"),
                BusStop(id = 59, line = 3, name = "Av. Padre Tacoronte"),
                BusStop(id = 60, line = 3, name = "Av. del Sol  5"),
                BusStop(id = 61, line = 3, name = "Av. del Sol  70b"),
                BusStop(id = 63, line = 3, name = "Av. Padre Tacoronte  24"),
                BusStop(id = 3, line = 3, name = "Complejo Campomayor"),
                BusStop(id = 4, line = 3, name = "Calle Nevero Cuatro"),
                BusStop(id = 5, line = 3, name = "Av. Antonio Nevado González  36"),
                BusStop(id = 6, line = 3, name = "Av. Antonio Nevado González  15"),
                BusStop(id = 7, line = 3, name = "Av. Antonio Nevado González"),
                BusStop(id = 8, line = 3, name = "Calle de Viriato  6"),
                BusStop(id = 9, line = 3, name = "Calle Perca"),
                BusStop(id = 10, line = 3, name = "Calle Gurugú  139"),
                BusStop(id = 21, line = 3, name = "Av. Padre Tacoronte  51"),
                BusStop(id = 22, line = 3, name = "Av. Padre Tacoronte  35"),
                BusStop(id = 23, line = 3, name = "Av. Padre Tacoronte  5"),
                BusStop(id = 24, line = 3, name = "Cardenal Cisneros  21"),
                BusStop(id = 25, line = 3, name = "Argüello Carvajal Teologo Siglo XVII  28"),
                BusStop(id = 26, line = 3, name = "Av. Carolina Coronado EST. FF.CC."),
                BusStop(id = 49, line = 3, name = "Av. Carolina Coronado  52"),
                BusStop(id = 51, line = 3, name = "Av. Carolina Coronado  74"),
                BusStop(id = 52, line = 3, name = "Av. Adolfo Díaz Ambrona  87"),
                BusStop(id = 227, line = 3, name = "Av. Santa Marina  40"),
                BusStop(id = 228, line = 3, name = "Av. Santa Marina  19"),
                BusStop(id = 229, line = 3, name = "Av. Santa Marina  11"),
                BusStop(id = 248, line = 3, name = "Calle Enrique Segura Otaño  3"),
                BusStop(id = 157, line = 3, name = "Av. de Europa  3)")
            )
        ),
        bigImage = false,
        onEvent = {},
        onNavigationEvent = {}
    )
}













