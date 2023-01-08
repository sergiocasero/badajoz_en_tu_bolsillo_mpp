package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.repository.CalendarRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CalendarViewModel(initialState: CalendarState) :
    RootViewModel<CalendarState, CalendarEvent>(initialState) {

    private val repository: CalendarRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = CalendarState.InProgress

            execute { repository.getCalendar() }.fold(
                error = { println("Error: ") },
                success = {
                    println("Success: $it")
                    _uiState.value = CalendarState.Success(it)
                }
            )
        }
    }

    override fun onEvent(event: CalendarEvent) {
        when (event) {
            CalendarEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class CalendarState : ViewState() {
    object InProgress : CalendarState()
    class Error(val error: AppError) : CalendarState()
    data class Success(val events: List<CalendarItem>) : CalendarState()

}

sealed class CalendarEvent {
    object Attach : CalendarEvent()
}
