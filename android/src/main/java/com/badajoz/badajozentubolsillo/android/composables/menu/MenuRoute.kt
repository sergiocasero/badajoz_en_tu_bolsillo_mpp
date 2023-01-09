package com.badajoz.badajozentubolsillo.android.composables.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.TopBar
import com.badajoz.badajozentubolsillo.android.composables.bike.BikeRoute
import com.badajoz.badajozentubolsillo.android.composables.bus.BusHomeRoute
import com.badajoz.badajozentubolsillo.android.composables.calendar.CalendarRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsRoute
import com.badajoz.badajozentubolsillo.android.composables.taxes.TaxesRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
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
fun MenuRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { MenuViewModel(MenuState.Bus) }

    MenuContent(
        state = viewModel.stateWithLifecycle().value,
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
            DrawerItem(title = MenuState.News.title(), icon = MenuState.News.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnNewsClick)
            }
            DrawerItem(title = MenuState.Calendar.title(), icon = MenuState.Calendar.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnCalendarClick)
            }
            DrawerItem(title = MenuState.Taxes.title(), icon = MenuState.Taxes.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnTaxesClick)
            }
            DrawerItem(title = MenuState.Bike.title(), icon = MenuState.Bike.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnBikeClick)
            }
            DrawerItem(title = MenuState.Bus.title(), icon = MenuState.Bus.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnBusClick)
            }
            DrawerItem(title = MenuState.Minits.title(), icon = MenuState.Minits.icon()) {
                coroutineScope.launch { scaffoldState.drawerState.close() }
                onEvent(MenuEvent.OnMinitsClick)
            }
            DrawerItem(title = MenuState.Pharmacy.title(), icon = MenuState.Pharmacy.icon()) {
                onEvent(MenuEvent.OnPharmacyClick)
            }
        }
    ) {
        when (state) {
            MenuState.News -> NewsRoute { onNavigationEvent(it) }
            MenuState.Bike -> BikeRoute()
            MenuState.Bus -> BusHomeRoute()
            MenuState.Calendar -> CalendarRoute()
            MenuState.Fmd -> TODO()
            MenuState.Minits -> TODO()
            MenuState.Pharmacy -> TODO()
            MenuState.Taxes -> TaxesRoute { onNavigationEvent(it) }
        }
    }
}

@Composable
fun DrawerItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
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