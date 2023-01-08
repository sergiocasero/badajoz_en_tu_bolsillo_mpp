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

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BikeState.InProgress

            execute { repository.getBikeStations() }.fold(
                error = { println("Error: ") },
                success = { println("Success: $it") }
            )
        }
    }

    override fun onEvent(event: BikeEvent) {
        when (event) {
            BikeEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class BikeState : ViewState() {
    object InProgress : BikeState()
    class Error(val error: AppError) : BikeState()
    data class Success(val bikeStations: List<BikeStation>) : BikeState()

}

sealed class BikeEvent {
    object Attach : BikeEvent()
}
