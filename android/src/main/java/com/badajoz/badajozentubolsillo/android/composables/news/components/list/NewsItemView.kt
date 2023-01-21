package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.bus.models.toColor
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.model.category.news.NewsCategory
import com.badajoz.badajozentubolsillo.utils.MaterialColor

@Composable
fun NewsItemView(news: News, onClick: () -> Unit) {
    DefaultCard(
        modifier = Modifier.clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 8.dp)
                        .border(1.dp, news.category.color.toColor(), RoundedCornerShape(16.dp)),
                    // shape = RoundedCornerShape(16.dp),
                    // color = news.category.color.toColor()
                ) {
                    Text(
                        text = news.category.name,
                        style = MaterialTheme.typography.subtitle2.apply {
                            copy(color = MaterialTheme.colors.onPrimary)
                        },
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    )
                }
                Text(
                    text = news.date,
                    style = MaterialTheme.typography.subtitle2.apply {
                        copy(color = MaterialTheme.colors.onBackground)
                    }
                )
            }
            Column {
                Text(
                    text = news.title
                        .toLowerCase(Locale.current)
                        .capitalize(Locale.current),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color(MaterialColor.GREY.tone(500, 50))
                ) {}
                Text(
                    text = news.description,
                    style = MaterialTheme.typography.body2,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsItemViewPreview() {
    NewsItemView(
        news = News(
            category = NewsCategory(
                id = "1",
                name = "Category 1",
                color = "#FFAA00"
            ),
            title = "Title 1",
            description = "Description 1",
            date = "2021-01-01",
            link = "https://picsum.photos/200/300"
        )
    ) {}
}