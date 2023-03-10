package com.badajoz.badajozentubolsillo.android.composables.bus.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.reusable.TopBar
import com.badajoz.badajozentubolsillo.android.utils.staticUrl
import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.badajoz.badajozentubolsillo.viewmodel.bus.BusLineDetailEvent

@Composable
fun BusLineDetailView(
    appConfigData: AppConfigData,
    title: String,
    imageRoute: String,
    bigImage: Boolean,
    stops: List<BusStop>,
    onEvent: (BusLineDetailEvent) -> Unit,
    onNavigate: (Destination) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = title, icon = Icons.Default.ArrowBack) {
                onNavigate(Screen.Menu.Bus.toDestination())
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onEvent(BusLineDetailEvent.OnImageClick) },
                modifier = Modifier.padding(16.dp),
                text = { Text(stringResource(id = if (bigImage) R.string.see_stops else R.string.see_route)) },
                icon = {
                    Icon(
                        if (bigImage) Icons.Default.List else Icons.Default.Search,
                        contentDescription = stringResource(id = if (bigImage) R.string.see_stops else R.string.see_route)
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
                    model = imageRoute.staticUrl(
                        user = appConfigData.user,
                        pass = appConfigData.pass,
                        context = LocalContext.current
                    ),
                    contentDescription = stringResource(id = R.string.route, title),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 80.dp)
                        .fillMaxWidth(),
                )
            } else {
                LazyColumn {
                    items(stops) { stop ->
                        StopItemView(stop = stop,
                            onClick = { onEvent(BusLineDetailEvent.OnStopClick(stop)) },
                            onFavoriteClick = { onEvent(BusLineDetailEvent.OnFavoriteClick(stop)) }
                        )
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
            BusStop(id = 53, line = 3, name = "Av. Adolfo D??az Ambrona  88"),
            BusStop(id = 54, line = 3, name = "Av. Carolina Coronado  4"),
            BusStop(id = 55, line = 3, name = "Av. Carolina Coronado  16"),
        ),
        onEvent = {},
        onNavigate = {},
        appConfigData = AppConfigData("", "", "", "", 0, true)
    )
}
