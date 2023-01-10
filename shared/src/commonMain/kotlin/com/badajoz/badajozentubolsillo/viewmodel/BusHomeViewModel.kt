package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.repository.BusRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusHomeViewModel(initialState: BusHomeState) :
    RootViewModel<BusHomeState, BusHomeEvent>(initialState) {

    private val repository: BusRepository by inject()

    private val busLines = mutableListOf<BusLineItem>()

    private val favoriteStops = mutableListOf<BusStop>()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusHomeState.InProgress

            execute { repository.getBusLines() }.fold(
                error = { _uiState.value = BusHomeState.Error(it) },
                success = {
                    busLines.addAll(it)
                    _uiState.value = BusHomeState.BusLines(it)
                }
            )

            execute { repository.getFavoriteBusStops() }.fold(
                error = { _uiState.value = BusHomeState.Error(it) },
                success = {
                    favoriteStops.addAll(it)
                }
            )
        }
    }

    override fun onEvent(event: BusHomeEvent) {
        when (event) {
            BusHomeEvent.Attach -> attach()
            BusHomeEvent.OnBusLinesClick -> _uiState.value = BusHomeState.BusLines(busLines)
            BusHomeEvent.OnFavoriteStopsClick -> _uiState.value = BusHomeState.FavoriteStops(favoriteStops)
            is BusHomeEvent.OnRemoveStopClick -> removeBusStopFromFavorites(event.stop)
        }
    }

    private fun removeBusStopFromFavorites(busStop: BusStop) {
        vmScope.launch {
            _uiState.value = BusHomeState.InProgress
            execute { repository.removeFavoriteStop(busStop) }.fold(
                error = {
                    println("Error: $it")
                    _uiState.value = BusHomeState.Error(it)
                },
                success = {
                    favoriteStops.remove(busStop)
                    _uiState.value = BusHomeState.FavoriteStops(favoriteStops)
                }
            )
        }
    }
}

sealed class BusHomeState : ViewState() {
    object InProgress : BusHomeState()
    class Error(val error: AppError) : BusHomeState()
    data class BusLines(val lines: List<BusLineItem>) : BusHomeState()
    data class FavoriteStops(val stops: List<BusStop>) : BusHomeState()
}

sealed class BusHomeEvent {
    object Attach : BusHomeEvent()
    object OnBusLinesClick : BusHomeEvent()
    object OnFavoriteStopsClick : BusHomeEvent()
    data class OnRemoveStopClick(val stop: BusStop) : BusHomeEvent()
}
