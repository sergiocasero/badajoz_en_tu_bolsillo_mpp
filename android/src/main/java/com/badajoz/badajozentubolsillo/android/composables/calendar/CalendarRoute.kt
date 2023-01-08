package com.badajoz.badajozentubolsillo.android.composables.calendar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.badajoz.badajozentubolsillo.android.composables.LoadingView
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.viewmodel.CalendarEvent
import com.badajoz.badajozentubolsillo.viewmodel.CalendarState
import com.badajoz.badajozentubolsillo.viewmodel.CalendarViewModel

@Composable
fun CalendarRoute() {
    val viewModel = remember { CalendarViewModel(initialState = CalendarState.InProgress) }

    CalendarContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun CalendarContent(state: CalendarState, onEvent: (CalendarEvent) -> Unit) {
    Scaffold(
        content = {
            when (state) {
                is CalendarState.InProgress -> LoadingView()
                is CalendarState.Error -> TODO()
                is CalendarState.Success -> CalendarSuccess(state.events)
            }
        }
    )
}

@Composable
fun CalendarSuccess(events: List<CalendarItem>) {
    LazyColumn {
        items(events.size) { index ->
            CalendarEventItem(events[index])
        }
    }
}

@Composable
fun CalendarEventItem(item: CalendarItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = item.title)
    }
}
