package com.badajoz.badajozentubolsillo.viewmodel.pharmacy


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup
import com.badajoz.badajozentubolsillo.repository.PharmacyRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class PharmacyViewModel(initialState: PharmacyState) :
    RootViewModel<PharmacyState, PharmacyEvent>(initialState) {

    private val repository: PharmacyRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = PharmacyState.InProgress

            execute { repository.getPharmacy() }.fold(
                error = { _uiState.value = PharmacyState.Error(it) },
                success = { _uiState.value = PharmacyState.Success(it) }
            )
        }
    }

    override fun onEvent(event: PharmacyEvent) {
        when (event) {
            PharmacyEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class PharmacyState : ViewState() {
    object InProgress : PharmacyState()
    class Error(val error: AppError) : PharmacyState()
    data class Success(val pharmacy: List<PharmacyGroup>) : PharmacyState()

}

sealed class PharmacyEvent {
    object Attach : PharmacyEvent()
}
