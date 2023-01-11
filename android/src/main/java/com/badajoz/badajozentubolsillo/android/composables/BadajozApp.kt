package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.badajoz.badajozentubolsillo.android.composables.bus.BusLineDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.fmd.FmdCenterDetailRoute
import com.badajoz.badajozentubolsillo.android.composables.menu.MenuRoute
import com.badajoz.badajozentubolsillo.android.composables.news.NewsDetailRoute
import com.badajoz.badajozentubolsillo.viewmodel.MenuState
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import java.net.URLDecoder

@Composable
fun BadajozApp(initialScreen: Screen, navController: NavHostController = rememberNavController()) {

    // val navigationViewModel = remember { NavigationViewModel(initialScreen) }

    NavHost(
        navController = navController,
        startDestination = initialScreen.route
    ) {


        composable(Screen.News.template) {
            MenuRoute(state = MenuState.News) {
                if (Screen.News.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Calendar.template) {
            MenuRoute(state = MenuState.Calendar) {
                if (Screen.Calendar.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Bus.template) {
            MenuRoute(state = MenuState.Bus) {
                if (Screen.Bus.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Bike.template) {
            MenuRoute(state = MenuState.Bike) {
                if (Screen.Bike.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Minits.template) {
            MenuRoute(state = MenuState.Minits) {
                if (Screen.Minits.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Fmd.template) {
            MenuRoute(state = MenuState.Fmd) {
                if (Screen.Fmd.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Pharmacy.template) {
            MenuRoute(state = MenuState.Pharmacy) {
                if (Screen.Pharmacy.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(Screen.Taxes.template) {
            MenuRoute(state = MenuState.Taxes) {
                if (Screen.Taxes.checkAccess(it)) {
                    navController.navigate(it.route)
                }
            }
        }

        composable(
            route = Screen.NewsDetail().template,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigate = {
                    if (Screen.NewsDetail().checkAccess(it)) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(
            route = Screen.BusLineDetail().template,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val lineId = requireNotNull(it.arguments).getInt("id")
            BusLineDetailRoute(
                lineId = lineId,
                onNavigate = {
                    if (Screen.BusLineDetail().checkAccess(it)) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(
            route = Screen.FmdCenterDetail().template,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val centerId = requireNotNull(it.arguments).getInt("id")
            FmdCenterDetailRoute(
                id = centerId,
                onNavigate = {
                    if (Screen.FmdCenterDetail().checkAccess(it)) {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}