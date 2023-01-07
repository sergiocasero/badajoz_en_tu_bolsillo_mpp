package com.badajoz.badajozentubolsillo.android.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.badajoz.badajozentubolsillo.android.composables.news.NewsRoute
import com.badajoz.badajozentubolsillo.android.utils.exhaustive
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.Home
import com.badajoz.badajozentubolsillo.viewmodel.NavigationState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationViewModel

@Composable
fun BadajozApp(navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel() }

    NavHost(
        navController = navController,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            NewsRoute(onNavigationEvent = navigationViewModel::onEvent)
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
        NavigationState.Home -> navController.navigate(Home.route)
    }.exhaustive
}