package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.news.components.detail.NewsDetailContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.Destination
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailState
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailViewModel
import com.badajoz.badajozentubolsillo.viewmodel.Screen

@Composable
fun NewsDetailRoute(link: String, onNavigate: (Destination) -> Unit) {
    val viewModel = remember { NewsDetailViewModel(link, NewsDetailState.InProgress) }

    NewsDetailContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) },
        onNavigate = onNavigate
    )
}
