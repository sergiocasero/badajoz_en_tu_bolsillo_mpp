package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.model.category.news.News

@Composable
fun NewsList(news: List<News>, onItemClick: (News) -> Unit, onLoadMore: () -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(count = news.size + 1) { index ->
            if (index == news.size) {
                Button(onClick = { onLoadMore() }) {
                    Text(stringResource(id = R.string.read_more))
                }
            } else {
                NewsItem(news = news[index]) { onItemClick(news[index]) }
            }
        }
    }
}