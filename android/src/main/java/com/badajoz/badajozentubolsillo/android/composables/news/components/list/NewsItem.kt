package com.badajoz.badajozentubolsillo.android.composables.news.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.bus.models.toColor
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.model.category.news.News

@Composable
fun NewsItem(news: News, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .clickable { onClick() },
        elevation = defaultCardElevation
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = news.category.color.toColor()
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
            Row {
                Column {
                    Text(
                        text = news.title
                            .toLowerCase(Locale.current)
                            .capitalize(Locale.current),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 4.dp),
                    )
                    Text(
                        text = news.description,
                        style = MaterialTheme.typography.body2,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}