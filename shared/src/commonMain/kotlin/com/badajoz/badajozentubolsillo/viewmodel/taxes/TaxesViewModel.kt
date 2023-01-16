package com.badajoz.badajozentubolsillo.viewmodel.taxes


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.repository.TaxRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class TaxesViewModel(initialState: TaxesViewModelState) :
    RootViewModel<TaxesViewModelState, TaxesViewModelEvent>(initialState) {

    private val repository: TaxRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = TaxesViewModelState.InProgress

            execute { repository.getTaxes() }.fold(
                error = { _uiState.value = TaxesViewModelState.Error(it) },
                success = { _uiState.value = TaxesViewModelState.Success(it) }
            )
        }
    }

    override fun onEvent(event: TaxesViewModelEvent) {
        when (event) {
            TaxesViewModelEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class TaxesViewModelState : ViewState() {
    object InProgress : TaxesViewModelState()
    class Error(val error: AppError) : TaxesViewModelState()
    data class Success(val taxes: List<TaxGroup>) : TaxesViewModelState()

}

sealed class TaxesViewModelEvent {
    object Attach : TaxesViewModelEvent()
}
