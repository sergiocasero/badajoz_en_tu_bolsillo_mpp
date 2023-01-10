package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation
import com.badajoz.badajozentubolsillo.repository.BikeRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BikeViewModel(initialState: BikeState) :
    RootViewModel<BikeState, BikeEvent>(initialState) {

    private val repository: BikeRepository by inject()

    private val bikeStations = mutableListOf<BikeStation>()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BikeState.InProgress

            execute { repository.getBikeStations() }.fold(
                error = { _uiState.value = BikeState.Error(it) },
                success = {
                    bikeStations.addAll(it)
                    _uiState.value = BikeState.Success(it, BikeViewType.Map)
                }
            )
        }
    }

    override fun onEvent(event: BikeEvent) {
        when (event) {
            BikeEvent.Attach -> attach()
            BikeEvent.OnBikeListClick -> _uiState.value = BikeState.Success(bikeStations, BikeViewType.List)
            BikeEvent.OnBikeMapClick -> _uiState.value = BikeState.Success(bikeStations, BikeViewType.Map)
        }.exhaustive
    }
}

sealed class BikeState : ViewState() {
    object InProgress : BikeState()
    class Error(val error: AppError) : BikeState()
    data class Success(val bikeStations: List<BikeStation>, val view: BikeViewType) : BikeState()

}

sealed class BikeEvent {
    object Attach : BikeEvent()
    object OnBikeMapClick : BikeEvent()
    object OnBikeListClick : BikeEvent()
}

enum class BikeViewType {
    List,
    Map
}
