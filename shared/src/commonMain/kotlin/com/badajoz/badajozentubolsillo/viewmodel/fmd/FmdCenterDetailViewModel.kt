package com.badajoz.badajozentubolsillo.viewmodel.fmd


import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterDetail
import com.badajoz.badajozentubolsillo.repository.FmdRepository
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewEvent
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FmdCenterDetailViewModel(val id: Int, initialState: FmdCenterDetailState) :
    RootViewModel<FmdCenterDetailState, FmdCenterDetailEvent>(initialState) {

    private val repository: FmdRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = FmdCenterDetailState.InProgress

            when (val result = appConfig.getAppConfigData()) {
                is Either.Left -> _uiState.value = FmdCenterDetailState.Error(result.error)
                is Either.Right -> execute { repository.getCenterDetail(id) }.fold(
                    error = { _uiState.value = FmdCenterDetailState.Error(it) },
                    success = { _uiState.value = FmdCenterDetailState.Success(appConfigData = result.success, it) }
                )
            }
        }
    }

    override fun onEvent(event: FmdCenterDetailEvent) {
        when (event) {
            FmdCenterDetailEvent.Attach -> attach()
        }
    }
}

sealed class FmdCenterDetailState : ViewState() {
    object InProgress : FmdCenterDetailState()
    class Error(val error: AppError) : FmdCenterDetailState()
    data class Success(val appConfigData: AppConfigData, val center: FmdCenterDetail) : FmdCenterDetailState()
}

sealed class FmdCenterDetailEvent : ViewEvent() {
    object Attach : FmdCenterDetailEvent()
}
