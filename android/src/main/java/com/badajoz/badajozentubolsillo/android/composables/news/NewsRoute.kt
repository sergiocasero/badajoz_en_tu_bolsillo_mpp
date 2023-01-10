package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.news.components.list.NewsContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.HomeState
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsViewModel

@Composable
fun NewsRoute(onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { NewsViewModel(HomeState.InProgress) }

    NewsContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}
