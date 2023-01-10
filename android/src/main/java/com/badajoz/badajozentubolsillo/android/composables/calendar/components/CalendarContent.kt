package com.badajoz.badajozentubolsillo.android.composables.calendar.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.badajoz.badajozentubolsillo.android.composables.reusable.ErrorView
import com.badajoz.badajozentubolsillo.android.composables.reusable.LoadingView
import com.badajoz.badajozentubolsillo.viewmodel.CalendarEvent
import com.badajoz.badajozentubolsillo.viewmodel.CalendarState

@Composable
fun CalendarContent(state: CalendarState, onEvent: (CalendarEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onEvent(CalendarEvent.Attach)
    }

    Scaffold(
        content = {
            when (state) {
                is CalendarState.InProgress -> LoadingView()
                is CalendarState.Error -> ErrorView(error = state.error) { onEvent(CalendarEvent.Attach) }
                is CalendarState.Success -> CalendarSuccess(state.events)
            }
        }
    )
}