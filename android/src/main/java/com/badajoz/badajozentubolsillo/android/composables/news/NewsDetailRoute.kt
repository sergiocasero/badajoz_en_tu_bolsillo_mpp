package com.badajoz.badajozentubolsillo.android.composables.news

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.composables.TopBar
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailState
import com.badajoz.badajozentubolsillo.viewmodel.NewsDetailViewModel

@Composable
fun NewsDetailRoute(link: String, onNavigationEvent: (NavigationEvent) -> Unit) {
    val viewModel = remember { NewsDetailViewModel(link, NewsDetailState.InProgress) }

    NewsDetailContent(
        state = viewModel.state.value,
        onEvent = { viewModel.onEvent(it) },
        onNavigationEvent = onNavigationEvent
    )
}

@Composable
fun NewsDetailContent(
    state: NewsDetailState,
    onEvent: (NewsDetailEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    Scaffold(topBar = {
        if (state is NewsDetailState.Success) {
            TopBar(title = state.newsDetail.title, icon = Icons.Default.ArrowBack, onNavClick = { })
        }
    },
        content = {
            when (state) {
                is NewsDetailState.InProgress -> LoadingView()
                is NewsDetailState.Success -> SuccessView(state.newsDetail)
                is NewsDetailState.Error -> TODO()
            }
        })
}

@Composable
fun SuccessView(newsDetail: NewsDetail) {
    Column {
        Text(text = newsDetail.content)
    }
}
