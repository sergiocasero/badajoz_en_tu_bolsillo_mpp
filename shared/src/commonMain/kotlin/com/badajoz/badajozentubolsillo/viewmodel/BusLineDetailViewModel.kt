package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineDetail
import com.badajoz.badajozentubolsillo.repository.BusRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class BusLineDetailViewModel(private val lineId: Int, initialState: BusLineDetailState) :
    RootViewModel<BusLineDetailState, BusLineDetailEvent>(initialState) {

    private val repository: BusRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = BusLineDetailState.InProgress

            execute { repository.getBusLineDetail(lineId) }.fold(
                error = { println("Error: ") },
                success = { _uiState.value = BusLineDetailState.Success(line = it, bigImage = false) }
            )
        }
    }

    override fun onEvent(event: BusLineDetailEvent) {
        when (event) {
            BusLineDetailEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class BusLineDetailState : ViewState() {
    object InProgress : BusLineDetailState()
    class Error(val error: AppError) : BusLineDetailState()
    data class Success(val line: BusLineDetail, val bigImage: Boolean) : BusLineDetailState()
}

sealed class BusLineDetailEvent {
    object Attach : BusLineDetailEvent()
}
