package com.badajoz.badajozentubolsillo.android.composables

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.badajoz.badajozentubolsillo.android.composables.bus.BusLineDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsDetailRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnBusLineDetail
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnMenu
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnNewsDetail
import com.badajoz.badajozentubolsillo.viewmodel.NavigationState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationViewModel
import java.net.URLDecoder

@Composable
fun BadajozApp(initialState: MenuState, navController: NavHostController = rememberNavController()) {

    val navigationViewModel = remember { NavigationViewModel(initialState) }

    NavHost(
        navController = navController,
        startDestination = OnMenu(initialState).createRoute()
    ) {

        MenuState.values().forEach { state ->
            composable(OnMenu(state).createRoute()) {
                MenuRoute(
                    state = state,
                    onNavigationEvent = navigationViewModel::onEvent
                )
            }
        }

        composable(
            route = OnNewsDetail().route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val arguments = requireNotNull(it.arguments)
            val link = arguments.getString("link")
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigationEvent = navigationViewModel::onEvent
            )
        }

        composable(
            route = OnBusLineDetail().route,
            arguments = listOf(navArgument("lineId") { type = NavType.IntType })
        ) {
            val arguments = requireNotNull(it.arguments)
            val lineId = arguments.getInt("lineId")
            BusLineDetailRoute(
                lineId = lineId,
                onNavigationEvent = navigationViewModel::onEvent
            )
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "BadajozApp: $state")

    when (state) {
        is MenuState -> navController.navigate(OnMenu(state).createRoute()) {
            popUpTo(OnMenu(state).createRoute()) { inclusive = true }
        }

        is NavigationState.NewsDetail -> navController.navigate(OnNewsDetail(state.link).createRoute())
        is NavigationState.ExternalLink -> {
            val context = LocalContext.current
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.link))
            context.startActivity(intent)
        }

        is NavigationState.BusLineDetail -> navController.navigate(OnBusLineDetail(state.lineId).createRoute())
        is NavigationState.MapLink -> {
            val context = LocalContext.current
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${state.address}"))
            context.startActivity(intent)
        }
    }
}