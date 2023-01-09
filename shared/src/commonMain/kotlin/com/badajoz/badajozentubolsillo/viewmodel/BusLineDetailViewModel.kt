package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.repository.BusRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusLineDetailViewModel(private val lineId: Int, initialState: BusLineDetailState) :
    RootViewModel<BusLineDetailState, BusLineDetailEvent>(initialState) {

    private val repository: BusRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusLineDetailState.InProgress

            execute { repository.getBusStops(lineId) }.fold(
                error = { println("Error: ") },
                success = { println("Error: ") }
            )
        }
    }

    override fun onEvent(event: BusLineDetailEvent) {
        when (event) {
            BusLineDetailEvent.Attach -> TODO()
        }.exhaustive
    }
}

sealed class BusLineDetailState : ViewState() {
    object InProgress : BusLineDetailState()
    class Error(val error: AppError) : BusLineDetailState()
    data class Success(val line: BusLine, val stops: List<BusStop>, val bigImage: Boolean) : BusLineDetailState()
}

sealed class BusLineDetailEvent {
    object Attach : BusLineDetailEvent()
}
