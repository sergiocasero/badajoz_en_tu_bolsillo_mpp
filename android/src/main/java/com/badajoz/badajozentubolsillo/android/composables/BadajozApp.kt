package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.badajoz.badajozentubolsillo.android.composables.bus.BusLineDetailRoute
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
        startDestination = Screen.News.route
    ) {


        composable(Screen.News.route) {
            MenuRoute(state = MenuState.News) {
                Screen.News.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Calendar.route) {
            MenuRoute(state = MenuState.Calendar) {
                Screen.Calendar.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Bus.route) {
            MenuRoute(state = MenuState.Bus) {
                Screen.Bus.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Bike.route) {
            MenuRoute(state = MenuState.Bike) {
                Screen.Bike.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Minits.route) {
            MenuRoute(state = MenuState.Minits) {
                Screen.Minits.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Fmd.route) {
            MenuRoute(state = MenuState.Fmd) {
                Screen.Fmd.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Pharmacy.route) {
            MenuRoute(state = MenuState.Pharmacy) {
                Screen.Pharmacy.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(Screen.Taxes.route) {
            MenuRoute(state = MenuState.Taxes) {
                Screen.Taxes.canNavigate(it) { navController.navigate(it.route) }
            }
        }

        composable(
            route = Screen.NewsDetail().route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigate = { Screen.NewsDetail().canNavigate(it) { navController.popBackStack() } }
            )
        }

        composable(
            route = Screen.BusLineDetail().route,
            arguments = listOf(navArgument("lineId") { type = NavType.IntType })
        ) {
            val lineId = requireNotNull(it.arguments).getInt("lineId")
            BusLineDetailRoute(
                lineId = lineId,
                onNavigate = { Screen.BusLineDetail().canNavigate(it) { navController.popBackStack() } }
            )
        }
    }
}