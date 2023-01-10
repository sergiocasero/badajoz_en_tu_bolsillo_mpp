package com.badajoz.badajozentubolsillo.android.composables.news.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import de.charlex.compose.HtmlText

@Composable
fun SuccessView(newsDetail: NewsDetail) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = newsDetail.img.url,
            contentDescription = newsDetail.img.alt,
            modifier = Modifier
                .fillMaxWidth()
        )
        HtmlText(text = newsDetail.content)
    }
}