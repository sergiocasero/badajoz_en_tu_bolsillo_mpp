package com.badajoz.badajozentubolsillo.android.composables.menu.models

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
}

fun MenuState.screen(): Screen = when (this) {
    MenuState.Bike -> Screen.Bike
    MenuState.Bus -> Screen.Bus
    MenuState.Calendar -> Screen.Calendar
    MenuState.Fmd -> Screen.Fmd
    MenuState.Minits -> Screen.Minits
    MenuState.News -> Screen.News
    MenuState.Pharmacy -> Screen.Pharmacy
    MenuState.Taxes -> Screen.Taxes
}