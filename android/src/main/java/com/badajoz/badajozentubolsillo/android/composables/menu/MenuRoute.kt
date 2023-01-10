package com.badajoz.badajozentubolsillo.android.composables.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.badajoz.badajozentubolsillo.android.composables.menu.components.MenuContent
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.MenuViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent



@Composable
fun MenuRoute(state: MenuState, onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { MenuViewModel(state) }

    MenuContent(
        state = state,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}
