package com.badajoz.badajozentubolsillo.android.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.Menu
import com.badajoz.badajozentubolsillo.viewmodel.NavigationState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationViewModel

@Composable
fun BadajozApp(navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel() }

    NavHost(
        navController = navController,
        startDestination = Menu.route
    ) {
        composable(route = Menu.route) {
            MenuRoute(onNavigationEvent = navigationViewModel::onEvent)
        }

        /* composable(route = Detail().route) {
            PoiDetailRoute(
                poiId = it.arguments!!.getString("poiId")!!.toLong(),
                navController = navController
            )
        } */
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "PoiApp: $state")

    when (state) {
        NavigationState.Menu -> navController.navigate(Menu.route)
    }.exhaustive
}