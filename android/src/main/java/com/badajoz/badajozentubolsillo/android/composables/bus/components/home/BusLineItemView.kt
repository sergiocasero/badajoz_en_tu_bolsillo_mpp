package com.badajoz.badajozentubolsillo.android.composables.bus.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.reusable.TextBox
import com.badajoz.badajozentubolsillo.android.composables.bus.models.toColor
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem

@Composable
fun BusLineItemView(line: BusLineItem, onLineClick: (BusLineItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(60.dp)
            .clickable { onLineClick(line) },
        elevation = defaultCardElevation
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextBox(label = line.name, color = line.color.toColor())
            Text(
                text = line.description,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                style = MaterialTheme.typography.body1
            )
        }
    }
}