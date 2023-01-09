package com.badajoz.badajozentubolsillo.android.composables

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.badajoz.badajozentubolsillo.android.composables.bus.BusLineDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsDetailRoute
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnMenu
import com.badajoz.badajozentubolsillo.viewmodel.NavigationState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationViewModel
import java.net.URLDecoder

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
                link = URLDecoder.decode(it.arguments!!.getString("link")!!, "UTF-8"),
                onNavigationEvent = navigationViewModel::onEvent
            )
        }

        composable(route = NavigationEvent.OnBusLineDetail().route) {
            BusLineDetailRoute(
                lineId = it.arguments!!.getString("lineId")!!.toInt()
            )
        }
    }

    val state = navigationViewModel.stateWithLifecycle().value

    Log.i("Composable app changed", "BadajozApp: $state")

    when (state) {
        NavigationState.Menu -> navController.navigate(OnMenu.route)
        is NavigationState.NewsDetail -> navController.navigate(NavigationEvent.OnNewsDetail(state.link).createRoute())
        is NavigationState.ExternalLink -> {
            val context = LocalContext.current
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.link))
            context.startActivity(intent)
        }

        is NavigationState.BusLineDetail -> navController.navigate(
            NavigationEvent.OnBusLineDetail(state.lineId).createRoute()
        )
    }.exhaustive
}