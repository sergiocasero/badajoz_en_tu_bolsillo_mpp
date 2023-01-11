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
            is FmdSportDetailEvent.DayClicked -> manageDayClick(event.dayId)
        }
    }

    private fun manageDayClick(dayId: Int) {
        val currentState = _uiState.value
        if (currentState is FmdSportDetailState.Success) {
            val days = currentState.sport.days
            val day = days.find { it.id == dayId }
            if (day != null) {
                val newDay = day.copy(expanded = !day.expanded)
                val newDays = days.map { if (it.id == dayId) newDay else it }
                _uiState.value = FmdSportDetailState.Success(currentState.sport.copy(days = newDays))
            }
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
    data class DayClicked(val dayId: Int) : FmdSportDetailEvent()
}
