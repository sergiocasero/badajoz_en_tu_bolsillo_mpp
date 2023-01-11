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
import com.badajoz.badajozentubolsillo.android.composables.fmd.FmdSportDetailRoute
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


        composable(Screen.News.route) {
            MenuRoute(state = MenuState.News) {
                if (Screen.News.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Calendar.route) {
            MenuRoute(state = MenuState.Calendar) {
                if (Screen.Calendar.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Bus.route) {
            MenuRoute(state = MenuState.Bus) {
                if (Screen.Bus.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Bike.route) {
            MenuRoute(state = MenuState.Bike) {
                if (Screen.Bike.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Minits.route) {
            MenuRoute(state = MenuState.Minits) {
                if (Screen.Minits.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Fmd.route) {
            MenuRoute(state = MenuState.Fmd) {
                if (Screen.Fmd.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Pharmacy.route) {
            MenuRoute(state = MenuState.Pharmacy) {
                if (Screen.Pharmacy.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(Screen.Taxes.route) {
            MenuRoute(state = MenuState.Taxes) {
                if (Screen.Taxes.checkAccess(it)) {
                    navController.navigate(it.to)
                }
            }
        }

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {
            val link = requireNotNull(it.arguments).getString("link")
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
            FmdCenterDetailRoute(
                id = centerId,
                onNavigate = {
                    if (Screen.FmdCenterDetail.checkAccess(it)) {
                        if (it.to == Screen.Fmd.route) {
                            navController.popBackStack()
                        } else {
                            navController.navigate(it.to)
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
            FmdSportDetailRoute(centerId = centerId, sportId = sportId) {
                if (Screen.FmdSportDetail.checkAccess(it)) {
                    navController.popBackStack()
                }
            }
        }
    }
}