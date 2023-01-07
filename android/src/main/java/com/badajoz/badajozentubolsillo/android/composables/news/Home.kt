package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.HomeEvent
import com.badajoz.badajozentubolsillo.viewmodel.HomeState
import com.badajoz.badajozentubolsillo.viewmodel.HomeViewModel
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import kotlin.reflect.KFunction1

@Composable
fun NewsRoute(onNavigationEvent: KFunction1<NavigationEvent, Unit>) {
    val viewModel = remember { HomeViewModel(HomeState.InProgress) }

    NewsContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun NewsContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {

}