package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.viewmodel.NewsEvent
import com.badajoz.badajozentubolsillo.viewmodel.Screen
import java.net.URLEncoder

@Composable
fun NewsSuccess(
    news: List<News>,
    onEvent: (NewsEvent) -> Unit,
    onNavigate: (Screen) -> Unit
) {
    NewsList(news,
        onItemClick = { onNavigate(Screen.NewsDetail(URLEncoder.encode(it.link, "UTF-8"))) },
        onLoadMore = { onEvent(NewsEvent.OnLoadMore) }
    )
}