package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineDetail
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.repository.BusRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusLineDetailViewModel(private val lineId: Int, initialState: BusLineDetailState) :
    RootViewModel<BusLineDetailState, BusLineDetailEvent>(initialState) {

    private val repository: BusRepository by inject()

    private var line: BusLineDetail? = null

    private var bigImage = false

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusLineDetailState.InProgress

            execute { repository.getBusLineDetail(lineId) }.fold(
                error = { println("Error: ") },
                success = {
                    line = it
                    _uiState.value = BusLineDetailState.Success(line = it, bigImage = bigImage)
                }
            )
        }
    }

    override fun onEvent(event: BusLineDetailEvent) {
        when (event) {
            BusLineDetailEvent.Attach -> attach()
            BusLineDetailEvent.OnImageClick -> line?.let {
                bigImage = !bigImage
                _uiState.value = BusLineDetailState.Success(line = it, bigImage = bigImage)
            }

            is BusLineDetailEvent.OnFavoriteClick -> updateFavoriteStatus(event.stop)
        }
    }

    private fun updateFavoriteStatus(stop: BusStop) {
        vmScope.launch {
            _uiState.value = BusLineDetailState.InProgress
            execute {
                when (stop.favorite) {
                    true -> repository.saveFavoriteStop(stop)
                    false -> repository.removeFavoriteStop(stop)
                }
            }.fold(
                error = { println("Error: ") },
                success = {
                    line?.stops?.find { it.id == stop.id }?.favorite = stop.favorite
                    line?.let {
                        _uiState.value = BusLineDetailState.Success(line = it, bigImage = bigImage)
                    }
                }
            )
        }
    }
}

sealed class BusLineDetailState : ViewState() {
    object InProgress : BusLineDetailState()
    class Error(val error: AppError) : BusLineDetailState()
    data class Success(val line: BusLineDetail, val bigImage: Boolean) : BusLineDetailState()
}

sealed class BusLineDetailEvent {
    object Attach : BusLineDetailEvent()
    object OnImageClick : BusLineDetailEvent()

    data class OnFavoriteClick(val stop: BusStop) : BusLineDetailEvent()
}
