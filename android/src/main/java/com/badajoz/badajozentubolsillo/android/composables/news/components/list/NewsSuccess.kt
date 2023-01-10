package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.viewmodel.NavigationEvent
import com.badajoz.badajozentubolsillo.viewmodel.NewsEvent
import java.net.URLEncoder

@Composable
fun NewsSuccess(news: List<News>, onEvent: (NewsEvent) -> Unit, onNavigationEvent: (NavigationEvent) -> Unit) {
    NewsList(news,
        onItemClick = {
            onNavigationEvent(NavigationEvent.OnNewsDetail(URLEncoder.encode(it.link, "UTF-8")))
        },
        onLoadMore = {
            onEvent(NewsEvent.OnLoadMore)
        })
}