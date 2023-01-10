package com.badajoz.badajozentubolsillo.android.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnBusLineDetail
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnMenu
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent.OnNewsDetail
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
                    onNavigationEvent = {
                        when (it) {
                            is NavigationEvent.OnBack -> navController.popBackStack()
                            is OnBusLineDetail -> navController.navigate(OnBusLineDetail(it.lineId).createRoute())
                            is OnMenu -> {
                                val route1 = it.createRoute()
                                navController.navigate(route1) {
                                    popUpTo(route1) { inclusive = true }
                                }
                            }

                            is OnNewsDetail -> navController.navigate(OnNewsDetail(it.link).createRoute())
                            is NavigationEvent.OnOpenExternalLink -> {
                                // val context = LocalContext.current
                                // val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.link))
                                // context.startActivity(intent)
                            }

                            is NavigationEvent.OnOpenMapLink -> {
                                // val context = LocalContext.current
                                // val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${state
                                // .address}"))
                                // context.startActivity(intent)
                            }
                        }
                    }
                )
            }
        }

        composable(
            route = OnNewsDetail().route,
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) { it ->
            val arguments = requireNotNull(it.arguments)
            val link = arguments.getString("link")
            NewsDetailRoute(
                link = URLDecoder.decode(link, "UTF-8"),
                onNavigationEvent = { navController.popBackStack() }
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
                onNavigationEvent = { navController.popBackStack() }
            )
        }
    }
}