package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.repository.BusRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusHomeViewModel(initialState: BusHomeState) :
    RootViewModel<BusHomeState, BusHomeEvent>(initialState) {

    private val repository: BusRepository by inject()

    private val busLines = mutableListOf<BusLine>()

    private val favoriteStops = mutableListOf<BusStop>()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusHomeState.InProgress

            execute { repository.getBusLines() }.fold(
                error = { println("Error: ") },
                success = {
                    busLines.addAll(it)
                    _uiState.value = BusHomeState.BusLines(it)
                }
            )

            execute { repository.getFavoriteBusStops() }.fold(
                error = { println("Error: ") },
                success = {
                    favoriteStops.addAll(it)
                    _uiState.value = BusHomeState.FavoriteStops(it)
                }
            )
        }
    }

    override fun onEvent(event: BusHomeEvent) {
        when (event) {
            BusHomeEvent.Attach -> attach()
            BusHomeEvent.OnBusLinesClick -> _uiState.value = BusHomeState.BusLines(busLines)
            BusHomeEvent.OnFavoriteStopsClick -> _uiState.value = BusHomeState.FavoriteStops(favoriteStops)
        }.exhaustive
    }
}

sealed class BusHomeState : ViewState() {
    object InProgress : BusHomeState()
    class Error(val error: AppError) : BusHomeState()
    data class BusLines(val lines: List<BusLine>) : BusHomeState()
    data class FavoriteStops(val stops: List<BusStop>) : BusHomeState()
}

sealed class BusHomeEvent {
    object Attach : BusHomeEvent()
    object OnBusLinesClick : BusHomeEvent()
    object OnFavoriteStopsClick : BusHomeEvent()
}
