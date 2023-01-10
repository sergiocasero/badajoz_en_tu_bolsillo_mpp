package com.badajoz.badajozentubolsillo.android.composables.calendar.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem

@Composable
fun CalendarSuccess(events: List<CalendarItem>) {
    LazyColumn {
        items(events.size) { index ->
            CalendarEventItem(events[index])
        }
    }
}