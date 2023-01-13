package com.badajoz.badajozentubolsillo.android.composables

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

        Screen.Menu.values().forEach { screen ->
            composable(screen.route) {
                coroutineScope.launch { analytics.logEvent(screen) }
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
                    screen.checkAccess(destination, granted = { navController.navigate(it) })
                    // TODO: External link on taxes and pharmacies
                }
            }
        }

        /*composable(Screen.Pharmacy.route) {
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
        }*/

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
            coroutineScope.launch { analytics.logEvent(Screen.NewsDetail, mapOf(Pair("link", link))) }
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigate = {
                    Screen.NewsDetail.checkAccess(it, back = { navController.popBackStack() })
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
                    Screen.BusLineDetail.checkAccess(it, back = { navController.popBackStack() })
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
                onNavigate = { destination ->
                    Screen.FmdCenterDetail.checkAccess(
                        destination,
                        back = { navController.popBackStack() },
                        granted = { navController.navigate(it) }
                    )
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
                Screen.FmdSportDetail.checkAccess(
                    destination = it,
                    back = { navController.popBackStack() }
                )
            }
        }
    }
}