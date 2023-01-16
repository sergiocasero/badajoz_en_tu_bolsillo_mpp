package com.badajoz.badajozentubolsillo.viewmodel.bike


import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation
import com.badajoz.badajozentubolsillo.repository.BikeRepository
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BikeViewModel(initialState: BikeState) :
    RootViewModel<BikeState, BikeEvent>(initialState) {

    private val repository: BikeRepository by inject()
    private val bikeStations = mutableListOf<BikeStation>()

    private var appConfigData: AppConfigData? = null

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BikeState.InProgress

            when (val result = appConfig.getAppConfigData()) {
                is Either.Left -> _uiState.value = BikeState.Error(result.error)
                is Either.Right -> execute { repository.getBikeStations() }.fold(
                    error = { _uiState.value = BikeState.Error(it) },
                    success = {
                        appConfigData = result.success
                        bikeStations.addAll(it)
                        _uiState.value = BikeState.Success(result.success, it, BikeViewType.Map)
                    }
                )
            }
        }
    }

    override fun onEvent(event: BikeEvent) {
        when (event) {
            BikeEvent.Attach -> attach()
            BikeEvent.OnBikeListClick -> appConfigData?.let {
                _uiState.value = BikeState.Success(it, bikeStations, BikeViewType.List)
            }

            BikeEvent.OnBikeMapClick -> appConfigData?.let {
                _uiState.value = BikeState.Success(it, bikeStations, BikeViewType.Map)
            }
        }
    }
}

sealed class BikeState : ViewState() {
    object InProgress : BikeState()
    class Error(val error: AppError) : BikeState()
    data class Success(val appConfigData: AppConfigData, val bikeStations: List<BikeStation>, val view: BikeViewType) :
        BikeState()

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
