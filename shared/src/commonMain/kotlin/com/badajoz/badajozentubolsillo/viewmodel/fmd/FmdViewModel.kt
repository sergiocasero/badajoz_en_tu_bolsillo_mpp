package com.badajoz.badajozentubolsillo.viewmodel.fmd


import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.badajoz.badajozentubolsillo.repository.FmdRepository
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewEvent
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
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
            FmdEvent.Logout -> doLogout()
        }
    }

    private fun doLogout() {
        vmScope.launch {
            execute { repository.logout() }.fold(
                error = { _uiState.value = FmdState.Error(it) },
                success = { _uiState.value = FmdState.NotLoggedIn }
            )
        }
    }

    private fun showLogin() {
        _uiState.value = FmdState.NotLoggedIn
    }

    private fun showSports() {
        vmScope.launch {
            when (val result = appConfig.getAppConfigData()) {
                is Either.Left -> _uiState.value = FmdState.Error(result.error)
                is Either.Right -> execute { repository.getCenters() }.fold(
                    error = { _uiState.value = FmdState.Error(it) },
                    success = { _uiState.value = FmdState.CenterList(appConfigData = result.success, it) }
                )
            }
        }
    }

    private fun doLoginAndGetSports(fmdUser: FmdUser) {
        vmScope.launch {
            execute { repository.saveUser(fmdUser) }.fold(
                error = { _uiState.value = FmdState.Error(it) },
                success = { showSports() }
            )
        }
    }
}

sealed class FmdState : ViewState() {
    object InProgress : FmdState()
    class Error(val error: AppError) : FmdState()
    data class CenterList(val appConfigData: AppConfigData, val centers: List<FmdCenterItem>) : FmdState()
    object NotLoggedIn : FmdState()

}

sealed class FmdEvent : ViewEvent() {
    object Attach : FmdEvent()
    data class Login(val username: String, val password: String) : FmdEvent()

    object Logout : FmdEvent()
}
