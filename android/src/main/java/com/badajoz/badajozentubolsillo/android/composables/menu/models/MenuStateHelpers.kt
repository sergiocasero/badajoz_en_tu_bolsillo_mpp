package com.badajoz.badajozentubolsillo.android.composables.menu.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusAlert
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun MenuState.title(): String = stringResource(
    id = when (this) {
        MenuState.Bike -> R.string.biba
        MenuState.Bus -> R.string.buses
        MenuState.Calendar -> R.string.calendar
        MenuState.Fmd -> R.string.fmd
        MenuState.Minits -> R.string.minits
        MenuState.News -> R.string.news
        MenuState.Pharmacy -> R.string.pharmacy
        MenuState.Taxes -> R.string.taxes
        MenuState.About -> R.string.about_app
    }
)

fun MenuState.icon(): ImageVector = when (this) {
    MenuState.Bike -> Icons.Filled.PedalBike
    MenuState.Bus -> Icons.Filled.BusAlert
    MenuState.Calendar -> Icons.Filled.CalendarToday
    MenuState.Fmd -> Icons.Filled.Sports
    MenuState.Minits -> Icons.Filled.ElectricCar
    MenuState.News -> Icons.Outlined.Newspaper
    MenuState.Pharmacy -> Icons.Filled.LocalPharmacy
    MenuState.Taxes -> Icons.Filled.Business
    MenuState.About -> Icons.Filled.Info
}

fun MenuState.screen(): Screen = when (this) {
    MenuState.Bike -> Screen.Menu.Bike
    MenuState.Bus -> Screen.Menu.Bus
    MenuState.Calendar -> Screen.Menu.Calendar
    MenuState.Fmd -> Screen.Menu.Fmd
    MenuState.Minits -> Screen.Menu.Minits
    MenuState.News -> Screen.Menu.News
    MenuState.Pharmacy -> Screen.Menu.Pharmacy
    MenuState.Taxes -> Screen.Menu.Taxes
    MenuState.About -> Screen.Menu.About
}