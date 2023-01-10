package com.badajoz.badajozentubolsillo.viewmodel


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSport
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.badajoz.badajozentubolsillo.repository.FmdRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FmdViewModel(initialState: FmdState) :
    RootViewModel<FmdState, FmdEvent>(initialState) {

    private val repository: FmdRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            execute { repository.isUserLoggedIn() }.fold(
                error = { _uiState.value = FmdState.Error(it) },
                success = { if (it) showSports() else showLogin() }
            )
        }
    }

    override fun onEvent(event: FmdEvent) {
        when (event) {
            FmdEvent.Attach -> attach()
            is FmdEvent.Login -> doLoginAndGetSports(FmdUser(event.username, event.password))
        }
    }

    private fun showLogin() {
        _uiState.value = FmdState.NotLoggedIn
    }

    private fun showSports() {
        TODO("Not yet implemented")
    }

    private fun doLoginAndGetSports(fmdUser: FmdUser) {

    }
}

sealed class FmdState : ViewState() {
    object InProgress : FmdState()
    class Error(val error: AppError) : FmdState()
    data class SportList(val availableSports: List<FmdSport>) : FmdState()
    object NotLoggedIn : FmdState()

}

sealed class FmdEvent {
    object Attach : FmdEvent()
    data class Login(val username: String, val password: String) : FmdEvent()
}
