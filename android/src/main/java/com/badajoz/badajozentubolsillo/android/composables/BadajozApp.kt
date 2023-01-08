package com.badajoz.badajozentubolsillo.android.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsDetailRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.NavigationAction
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnMenu
import com.badajoz.badajozentubolsillo.viewmodel.NavigationState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationViewModel

@Composable
fun BadajozApp(navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel() }

    NavHost(
        navController = navController,
        startDestination = OnMenu.route
    ) {
        composable(route = OnMenu.route) {
            MenuRoute(onNavigationEvent = navigationViewModel::onEvent)
        }

        composable(route = NavigationEvent.OnNewsDetail().route) {
            NewsDetailRoute(
                link = it.arguments!!.getString("link")!!,
                onNavigationEvent = navigationViewModel::onEvent
            )
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "BadajozApp: $state")

    when (state) {
        NavigationState.Menu -> navController.navigate(OnMenu.route)
        is NavigationState.NewsDetail -> navController.navigate(NavigationEvent.OnNewsDetail(state.link).createRoute())
    }.exhaustive

    when (navigationViewModel.actions.collectAsState(initial = NavigationAction.Dummy).value) {
        NavigationAction.Back -> navController.popBackStack()
        NavigationAction.Dummy -> {
            // Do nothing
        }
    }
}