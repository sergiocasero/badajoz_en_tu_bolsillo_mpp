package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.flow.cStateFlow
import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.model.category.bus.BusStopDetail
import com.badajoz.badajozentubolsillo.repository.BusRepository
import com.badajoz.badajozentubolsillo.utils.withItemUpdated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusLineDetailViewModel(private val lineId: Int, initialState: BusLineDetailState) :
    RootViewModel<BusLineDetailState, BusLineDetailEvent>(initialState) {

    private val repository: BusRepository by inject()
    private val _bigImageState = MutableStateFlow(false)
    private val _busStopsState = MutableStateFlow<MutableList<BusStop>>(mutableListOf())

    val bigImageState = _bigImageState.cStateFlow()
    val busStopsState = _busStopsState.cStateFlow()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusLineDetailState.InProgress

            when (val result = appConfig.getAppConfigData()) {
                is Either.Left -> _uiState.value = BusLineDetailState.Error(result.error)
                is Either.Right -> execute { repository.getBusLineDetail(lineId) }.fold(
                    error = { _uiState.value = BusLineDetailState.Error(it) },
                    success = {
                        _busStopsState.value = it.stops.toMutableList()
                        _uiState.value = BusLineDetailState.Success(
                            appConfigData = result.success,
                            title = it.name,
                            imageRoute = it.image
                        )
                    }
                )
            }
        }
    }

    override fun onEvent(event: BusLineDetailEvent) {
        when (event) {
            BusLineDetailEvent.Attach -> attach()
            BusLineDetailEvent.OnImageClick -> _bigImageState.value = !_bigImageState.value
            is BusLineDetailEvent.OnFavoriteClick -> updateFavoriteStatus(event.stop)
            is BusLineDetailEvent.OnStopClick -> onStopClick(event.stop)
            BusLineDetailEvent.OnDismissDialogClick -> onDismissDialogClick()
        }
    }

    private fun onDismissDialogClick() {
        val currentState = _uiState.value
        if (currentState is BusLineDetailState.Success) {
            _uiState.value = currentState.copy(selectedStop = null)
        }
    }

    private fun onStopClick(stop: BusStop) {
        val currentState = _uiState.value
        if (currentState is BusLineDetailState.Success) {

            vmScope.launch {
                execute { repository.getStopTimes(lineId, stop.id) }.fold(
                    error = { _uiState.value = BusLineDetailState.Error(it) },
                    success = {
                        _uiState.value = currentState.copy(selectedStop = BusStopDetail(stop, it))
                    }
                )
            }
        }
    }

    private fun updateFavoriteStatus(stop: BusStop) {
        val newStop = stop.copy(favorite = !stop.favorite)
        vmScope.launch {
            execute {
                when (newStop.favorite) {
                    true -> repository.saveFavoriteStop(newStop)
                    false -> repository.removeFavoriteStop(newStop)
                }
            }.fold(
                error = { _uiState.value = BusLineDetailState.Error(it) },
                success = {
                    _busStopsState.value = _busStopsState
                        .withItemUpdated(stop.copy(favorite = newStop.favorite)) { it.id == stop.id }
                }
            )
        }
    }
}

sealed class BusLineDetailState : ViewState() {
    object InProgress : BusLineDetailState()
    class Error(val error: AppError) : BusLineDetailState()
    data class Success(
        val appConfigData: AppConfigData,
        val title: String,
        val imageRoute: String,
        val selectedStop: BusStopDetail? = null,
    ) : BusLineDetailState()
}

sealed class BusLineDetailEvent {
    object Attach : BusLineDetailEvent()
    object OnImageClick : BusLineDetailEvent()

    data class OnFavoriteClick(val stop: BusStop) : BusLineDetailEvent()

    data class OnStopClick(val stop: BusStop) : BusLineDetailEvent()

    object OnDismissDialogClick : BusLineDetailEvent()
}
