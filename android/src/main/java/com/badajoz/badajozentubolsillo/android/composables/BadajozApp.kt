package com.badajoz.badajozentubolsillo.android.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.badajoz.badajozentubolsillo.analytics.Analytics
import com.badajoz.badajozentubolsillo.analytics.SharedAnalytics
import com.badajoz.badajozentubolsillo.android.composables.bus.BusLineDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.fmd.FmdCenterDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.fmd.FmdSportDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsDetailRoute
import com.badajoz.badajozentubolsillo.viewmodel.NavigationGraph
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import com.badajoz.badajozentubolsillo.viewmodel.menu.MenuState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLDecoder

private fun openExternalLink(context: Context, url: String) {
    val to = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, to)
    context.startActivity(intent)
}

private fun openMaps(context: Context, url: String) {
    val to = Uri.parse("google.navigation:q=$url")
    val intent = Intent(Intent.ACTION_VIEW, to)
    context.startActivity(intent)
}

@Composable
private fun logEvent(
    scope: CoroutineScope, analytics: Analytics, screen: Screen, params: Map<String, Int> = emptyMap()
) {
    LaunchedEffect("logEvent") {
        scope.launch {
            analytics.logEvent(screen, params)
        }
    }
}

@Composable
fun BadajozApp(initialScreen: Screen, navController: NavHostController = rememberNavController()) {

    // val navigationViewModel = remember { NavigationViewModel(initialScreen) }
    val coroutineScope = rememberCoroutineScope()
    val navigationGraph = remember { NavigationGraph() }
    val analytics = remember<Analytics> { SharedAnalytics() }
    val context = LocalContext.current

    NavHost(
        navController = navController, startDestination = initialScreen.route
    ) {

        Screen.Menu.values().forEach { screen ->
            composable(screen.route) {
                logEvent(coroutineScope, analytics, screen)
                MenuRoute(
                    state = when (screen) {
                        Screen.Menu.About -> MenuState.About
                        Screen.Menu.Bike -> MenuState.Bike
                        Screen.Menu.Bus -> MenuState.Bus
                        Screen.Menu.Calendar -> MenuState.Calendar
                        Screen.Menu.Fmd -> MenuState.Fmd
                        Screen.Menu.Minits -> MenuState.Minits
                        Screen.Menu.News -> MenuState.News
                        Screen.Menu.Pharmacy -> MenuState.Pharmacy
                        Screen.Menu.Taxes -> MenuState.Taxes
                    }
                ) { destination ->
                    navigationGraph.checkPermission(from = screen,
                        to = destination.screen,
                        route = destination.route,
                        onGranted = { navController.navigate(it) },
                        onLink = { openExternalLink(context, it) },
                        onMap = { openMaps(context, it) })
                }
            }
        }

        composable(
            route = Screen.NewsDetail.route, arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
            logEvent(coroutineScope, analytics, Screen.NewsDetail)
            NewsDetailRoute(link = URLDecoder.decode(link, "UTF-8"), onNavigate = {
                navigationGraph.checkPermission(from = Screen.NewsDetail,
                    to = it.screen,
                    route = it.route,
                    onBack = { navController.popBackStack() },
                    onGranted = { navController.navigate(it) },
                    onLink = { openExternalLink(context, it) })
            })
        }

        composable(
            route = Screen.BusLineDetail.route, arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val lineId = requireNotNull(it.arguments).getInt("id")
            logEvent(coroutineScope, analytics, Screen.BusLineDetail, mapOf(Pair("lineId", lineId)))
            BusLineDetailRoute(lineId = lineId, onNavigate = {
                navigationGraph.checkPermission(from = Screen.BusLineDetail,
                    to = it.screen,
                    route = it.route,
                    onBack = { navController.popBackStack() },
                    onGranted = { navController.navigate(it) },
                    onLink = { openExternalLink(context, it) })
            })
        }

        composable(
            route = Screen.FmdCenterDetail.route, arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val centerId = requireNotNull(it.arguments).getInt("id")
            logEvent(coroutineScope, analytics, Screen.FmdCenterDetail, mapOf(Pair("sportId", centerId)))
            FmdCenterDetailRoute(id = centerId, onNavigate = { destination ->
                navigationGraph.checkPermission(from = Screen.FmdCenterDetail,
                    to = destination.screen,
                    route = destination.route,
                    onBack = { navController.popBackStack() },
                    onGranted = { navController.navigate(it) },
                    onLink = { openExternalLink(context, it) })
            })
        }

        composable(route = Screen.FmdSportDetail.route,
            arguments = listOf(navArgument("centerId") { type = NavType.IntType },
                navArgument("sportId") { type = NavType.IntType })) {
            val requireNotNull = requireNotNull(it.arguments)
            val centerId = requireNotNull.getInt("centerId")
            val sportId = requireNotNull.getInt("sportId")
            logEvent(
                coroutineScope,
                analytics,
                Screen.FmdSportDetail,
                mapOf(Pair("centerId", centerId), Pair("sportId", sportId))
            )
            FmdSportDetailRoute(centerId = centerId, sportId = sportId) {
                navigationGraph.checkPermission(from = Screen.FmdSportDetail,
                    to = it.screen,
                    route = it.route,
                    onBack = { navController.popBackStack() },
                    onGranted = { navController.navigate(it) },
                    onLink = { openExternalLink(context, it) })
            }
        }
    }
}