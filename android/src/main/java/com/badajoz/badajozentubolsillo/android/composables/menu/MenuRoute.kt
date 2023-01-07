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
import com.badajoz.badajozentubolsillo.android.composables.news.NewsRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.MenuEvent
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.MenuViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import kotlinx.coroutines.launch

@Composable
fun MenuRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { MenuViewModel(MenuState.News) }

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
            TopBar(title = "Noticias", icon = Icons.Default.Menu) {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            DrawerItem(title = "Noticias", icon = Icons.Outlined.Newspaper) { onEvent(MenuEvent.OnNewsClick) }
            DrawerItem(title = "Calendario", icon = Icons.Default.CalendarToday) { onEvent(MenuEvent.OnCalendarClick) }
            DrawerItem(title = "Trámites/Sede electrónica", icon = Icons.Default.Business) {
                onEvent(MenuEvent.OnCalendarClick)
            }
            DrawerItem(title = "Bicicletas BiBa", icon = Icons.Default.PedalBike) { onEvent(MenuEvent.OnBikeClick) }
            DrawerItem(title = "Autobuses", icon = Icons.Default.BusAlert) { onEvent(MenuEvent.OnBusClick) }
            DrawerItem(title = "Minits", icon = Icons.Default.ElectricCar) { onEvent(MenuEvent.OnMinitsClick) }
            DrawerItem(title = "Farmacias", icon = Icons.Default.LocalPharmacy) { onEvent(MenuEvent.OnPharmacyClick) }
        },
        content = {
            when (state) {
                MenuState.News -> NewsRoute { onNavigationEvent(it) }
                MenuState.Bike -> TODO()
                MenuState.Bus -> TODO()
                MenuState.Calendar -> TODO()
                MenuState.Fmd -> TODO()
                MenuState.Minits -> TODO()
                MenuState.Pharmacy -> TODO()
                MenuState.Taxes -> TODO()
            }
        }
    )
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