package com.badajoz.badajozentubolsillo.android.composables.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.android.composables.TopBar
import com.badajoz.badajozentubolsillo.android.composables.bike.BikeRoute
import com.badajoz.badajozentubolsillo.android.composables.bus.BusHomeRoute
import com.badajoz.badajozentubolsillo.android.composables.calendar.CalendarRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsRoute
import com.badajoz.badajozentubolsillo.android.composables.pharmacy.PharmacyRoute
import com.badajoz.badajozentubolsillo.android.composables.taxes.TaxesRoute
import com.badajoz.badajozentubolsillo.viewmodel.MenuEvent
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.MenuViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import kotlinx.coroutines.launch

fun MenuState.title(): String = when (this) {
    MenuState.Bike -> "Bicicletas BiBa"
    MenuState.Bus -> "Autobuses"
    MenuState.Calendar -> "Calendario"
    MenuState.Fmd -> "Reservas FMD"
    MenuState.Minits -> "Minits"
    MenuState.News -> "Noticias"
    MenuState.Pharmacy -> "Farmacias"
    MenuState.Taxes -> "Trámites/Sede electrónica"
}

fun MenuState.icon(): ImageVector = when (this) {
    MenuState.Bike -> Icons.Filled.PedalBike
    MenuState.Bus -> Icons.Filled.BusAlert
    MenuState.Calendar -> Icons.Filled.CalendarToday
    MenuState.Fmd -> Icons.Filled.Sports
    MenuState.Minits -> Icons.Filled.ElectricCar
    MenuState.News -> Icons.Outlined.Newspaper
    MenuState.Pharmacy -> Icons.Filled.LocalPharmacy
    MenuState.Taxes -> Icons.Filled.Business
}

@Composable
fun MenuRoute(state: MenuState, onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { MenuViewModel(state) }

    MenuContent(
        state = state,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun MenuContent(
    state: MenuState,
    onEvent: (MenuEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(title = state.title(), icon = Icons.Default.Menu) {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colors.secondary)
            ) {

                item {
                    Spacer(modifier = Modifier.size(24.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painterResource(id = R.drawable.badajoz_logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(50.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Text(
                            text = "Badajoz en tu bolsillo",
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                            .background(Color.White)
                    ) {
                        Column {
                            MenuState.values().forEach() {
                                DrawerItem(title = it.title(), icon = it.icon(), isCurrent = it == state) {
                                    coroutineScope.launch { scaffoldState.drawerState.close() }
                                    onNavigationEvent(NavigationEvent.OnMenu(it))
                                }
                            }
                        }
                    }
                }
            }
        }
    ) {
        when (state) {
            MenuState.News -> NewsRoute { onNavigationEvent(it) }
            MenuState.Bike -> BikeRoute { onNavigationEvent(it) }
            MenuState.Bus -> BusHomeRoute { onNavigationEvent(it) }
            MenuState.Calendar -> CalendarRoute()
            MenuState.Fmd -> TODO()
            MenuState.Minits -> TODO()
            MenuState.Pharmacy -> PharmacyRoute { onNavigationEvent(it) }
            MenuState.Taxes -> TaxesRoute { onNavigationEvent(it) }
        }
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, isCurrent: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = if (isCurrent) MaterialTheme.colors.primary else MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title)
        }
    }
}