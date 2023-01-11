package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportDetail
import com.badajoz.badajozentubolsillo.repository.FmdRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FmdSportDetailViewModel(private val centerId: Int, private val sportId: Int, initialState: FmdSportDetailState) :
    RootViewModel<FmdSportDetailState, FmdSportDetailEvent>(initialState) {

    private val repository: FmdRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = FmdSportDetailState.InProgress

            execute { repository.getSportDetail(centerId, sportId) }.fold(
                error = { _uiState.value = FmdSportDetailState.Error(it) },
                success = { _uiState.value = FmdSportDetailState.Success(it) }
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
    class Success(val sport: FmdSportDetail) : FmdSportDetailState()

}

sealed class FmdSportDetailEvent {
    object Attach : FmdSportDetailEvent()
}
