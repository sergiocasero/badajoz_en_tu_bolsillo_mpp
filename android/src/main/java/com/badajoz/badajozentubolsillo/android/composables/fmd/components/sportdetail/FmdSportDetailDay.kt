package com.badajoz.badajozentubolsillo.android.composables.fmd.components.sportdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportDay
import com.badajoz.badajozentubolsillo.utils.MaterialColor

@Composable
fun FmdSportDetailDay(day: FmdSportDay) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = defaultCardElevation
    ) {

        val slots = day.data

        // separate slots by court

        val hours = slots.groupBy { it.hour }
        val courts = slots.groupBy { it.court }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = day.title,
                style = MaterialTheme.typography.h6,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column {
                    courts.forEach {
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(38.dp)
                                .padding(1.dp)
                        ) {
                            Text(
                                it.key,
                                style = MaterialTheme.typography.subtitle2,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                    }
                }

                LazyRow {
                    items(hours.toList()) { hourSlot ->
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                hourSlot.first.split(" ")[0],
                                style = MaterialTheme.typography.subtitle2
                            )
                            hourSlot.second.forEach { slot ->
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .padding(1.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(
                                            Color(
                                                if (slot.available) {
                                                    MaterialColor.GREEN.tone(500)
                                                } else {
                                                    MaterialColor.RED.tone(500)
                                                }
                                            )
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}