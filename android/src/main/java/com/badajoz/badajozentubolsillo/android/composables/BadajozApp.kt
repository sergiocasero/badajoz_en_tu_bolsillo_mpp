package com.badajoz.badajozentubolsillo.android.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
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
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import kotlinx.coroutines.launch
import java.net.URLDecoder

@Composable
fun BadajozApp(initialScreen: Screen, navController: NavHostController = rememberNavController()) {

    // val navigationViewModel = remember { NavigationViewModel(initialScreen) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val analytics = remember<Analytics> { SharedAnalytics() }

    NavHost(
        navController = navController,
        startDestination = initialScreen.route
    ) {

        composable(Screen.News.route) {
            coroutineScope.launch { analytics.logEvent(Screen.News) }
            MenuRoute(state = MenuState.News) {
                if (Screen.News.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Calendar.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Calendar) }
            MenuRoute(state = MenuState.Calendar) {
                if (Screen.Calendar.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Bus.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Bus) }
            MenuRoute(state = MenuState.Bus) {
                if (Screen.Bus.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Bike.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Bike) }
            MenuRoute(state = MenuState.Bike) {
                if (Screen.Bike.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Minits.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Minits) }
            MenuRoute(state = MenuState.Minits) {
                if (Screen.Minits.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Fmd.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Fmd) }
            MenuRoute(state = MenuState.Fmd) {
                if (Screen.Fmd.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.About.route) {
            coroutineScope.launch { analytics.logEvent(Screen.About) }
            MenuRoute(state = MenuState.About) {
                if (Screen.About.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Pharmacy.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Pharmacy) }
            MenuRoute(state = MenuState.Pharmacy) {
                if (Screen.Pharmacy.checkAccess(it)) {
                    when (it.template) {
                        Screen.MapLink.route -> {
                            coroutineScope.launch {
                                // intent to open navigation maps, action should be "google.navigation:q="
                                // TODO("I don't like this")
                                val to = Uri.parse("google.navigation:q=${it.to.replace("map/", "")}")
                                val intent = Intent(Intent.ACTION_VIEW, to)
                                context.startActivity(intent)
                                // TODO ("I don't like this")
                            }
                        }

                        else -> navController.navigate(it.to)
                    }
                }
            }
        }

        composable(Screen.Taxes.route) {
            coroutineScope.launch { analytics.logEvent(Screen.Taxes) }
            MenuRoute(state = MenuState.Taxes) {
                if (Screen.Taxes.checkAccess(it)) {
                    when (it.template) {
                        Screen.ExternalLink.route -> {
                            coroutineScope.launch {
                                // intent to open external link
                                // TODO ("I don't like this")
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.to.replace("external/", "")))
                                context.startActivity(intent)
                            }
                        }

                        else -> navController.navigate(it.to)
                    }
                }
            }
        }

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
            coroutineScope.launch { analytics.logEvent(Screen.NewsDetail, mapOf(Pair("link", link))) }
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigate = {
                    if (Screen.NewsDetail.checkAccess(it)) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(
            route = Screen.BusLineDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val lineId = requireNotNull(it.arguments).getInt("id")
            coroutineScope.launch { analytics.logEvent(Screen.BusLineDetail, mapOf(Pair("id", lineId))) }
            BusLineDetailRoute(
                lineId = lineId,
                onNavigate = {
                    if (Screen.BusLineDetail.checkAccess(it)) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(
            route = Screen.FmdCenterDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val centerId = requireNotNull(it.arguments).getInt("id")
            coroutineScope.launch { analytics.logEvent(Screen.FmdCenterDetail, mapOf(Pair("id", centerId))) }
            FmdCenterDetailRoute(
                id = centerId,
                onNavigate = {
                    if (Screen.FmdCenterDetail.checkAccess(it)) {
                        when (it.template) {
                            Screen.Fmd.route -> navController.popBackStack()
                            Screen.FmdSportDetail.route -> navController.navigate(it.to)
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.FmdSportDetail.route,
            arguments = listOf(
                navArgument("centerId") { type = NavType.IntType },
                navArgument("sportId") { type = NavType.IntType }
            )
        ) {
            val requireNotNull = requireNotNull(it.arguments)
            val centerId = requireNotNull.getInt("centerId")
            val sportId = requireNotNull.getInt("sportId")
            coroutineScope.launch {
                analytics.logEvent(
                    Screen.FmdSportDetail,
                    mapOf(Pair("centerId", centerId), Pair("sportId", sportId))
                )
            }
            FmdSportDetailRoute(centerId = centerId, sportId = sportId) {
                if (Screen.FmdSportDetail.checkAccess(it)) {
                    navController.popBackStack()
                }
            }
        }
    }
}