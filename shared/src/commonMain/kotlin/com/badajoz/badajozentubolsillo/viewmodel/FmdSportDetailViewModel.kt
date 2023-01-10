package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.repository.FmdRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FmdSportDetailViewModel(private val sportId: Int, initialState: FmdSportDetailState) :
    RootViewModel<FmdSportDetailState, FmdSportDetailEvent>(initialState) {

    private val repository: FmdRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = FmdSportDetailState.InProgress

            execute { repository.getSportDetail(sportId) }.fold(
                error = { println("Error: ") },
                success = { println("Success: ") }
            )
        }
    }

    override fun onEvent(event: FmdSportDetailEvent) {
        when (event) {
            FmdSportDetailEvent.Attach -> attach()
        }
    }
}

sealed class FmdSportDetailState : ViewState() {
    object InProgress : FmdSportDetailState()
    class Error(val error: AppError) : FmdSportDetailState()
    object Success : FmdSportDetailState()

}

sealed class FmdSportDetailEvent {
    object Attach : FmdSportDetailEvent()
}
