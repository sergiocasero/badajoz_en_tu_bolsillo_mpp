package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.badajoz.badajozentubolsillo.android.R
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.model.category.news.NewsCategory

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
                NewsItemView(news = news[index]) { onItemClick(news[index]) }
            }
        }
    }
}

@Preview
@Composable
fun NewsListPreview() {
    NewsList(
        news = listOf(
            News(
                category = NewsCategory(
                    id = "1",
                    name = "Category 1",
                    color = "#FFAA00"
                ),
                title = "Title 1",
                description = "Description 1",
                date = "2021-01-01",
                link = "https://picsum.photos/200/300"
            ),
            News(
                category = NewsCategory(
                    id = "1",
                    name = "Category 1",
                    color = "#FFBB00"
                ),
                title = "Title 1",
                description = "Description 1",
                date = "2021-01-01",
                link = "https://picsum.photos/200/300"
            ),
            News(
                category = NewsCategory(
                    id = "1",
                    name = "Category 1",
                    color = "#CCAA00"
                ),
                title = "Title 1",
                description = "Description 1",
                date = "2021-01-01",
                link = "https://picsum.photos/200/300"
            ),
        ),
        onItemClick = {},
        onLoadMore = {}
    )
}