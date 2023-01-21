package com.badajoz.badajozentubolsillo.android.composables.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.badajoz.badajozentubolsillo.android.composables.reusable.DefaultCard
import com.badajoz.badajozentubolsillo.android.composables.reusable.FieldRow
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.utils.MaterialColor

@Composable
fun CalendarEventItem(item: CalendarItem) {
    DefaultCard {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = item.title, style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color(MaterialColor.GREY.tone(500, 50))
            ) {}
            if (item.date.isNotBlank()) {
                FieldRow(label = item.date, icon = Icons.Default.CalendarToday)
            }
            if (item.time.isNotBlank()) {
                FieldRow(label = item.time, icon = Icons.Default.Timer)
            }
            if (item.location.isNotBlank()) {
                FieldRow(label = item.location, icon = Icons.Default.LocationOn)
            }
        }
    }
}