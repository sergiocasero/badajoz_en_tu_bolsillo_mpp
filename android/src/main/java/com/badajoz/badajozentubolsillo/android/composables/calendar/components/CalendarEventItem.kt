package com.badajoz.badajozentubolsillo.android.composables.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.utils.defaultCardElevation
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem

@Composable
fun CalendarEventItem(item: CalendarItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        elevation = defaultCardElevation
    ) {
        Column {
            Text(
                text = item.title, style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(4.dp)
            )
            if (item.date.isNotBlank()) {
                CalendarFieldRow(label = item.date, icon = Icons.Default.CalendarToday)
            }
            if (item.time.isNotBlank()) {
                CalendarFieldRow(label = item.time, icon = Icons.Default.Timer)
            }
            if (item.location.isNotBlank()) {
                CalendarFieldRow(label = item.location, icon = Icons.Default.LocationOn)
            }
        }
    }
}