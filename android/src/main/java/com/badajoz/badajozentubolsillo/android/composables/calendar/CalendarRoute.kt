package com.badajoz.badajozentubolsillo.android.composables.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.badajoz.badajozentubolsillo.android.composables.calendar.components.CalendarContent
import com.badajoz.badajozentubolsillo.android.utils.stateWithLifecycle
import com.badajoz.badajozentubolsillo.viewmodel.calendar.CalendarState
import com.badajoz.badajozentubolsillo.viewmodel.calendar.CalendarViewModel

@Composable
fun CalendarRoute() {
    val viewModel = remember { CalendarViewModel(initialState = CalendarState.InProgress) }

    CalendarContent(
        state = viewModel.stateWithLifecycle().value,
        onEvent = { viewModel.onEvent(it) }
    )
}
