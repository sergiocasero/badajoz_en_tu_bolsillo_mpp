package com.badajoz.badajozentubolsillo.viewmodel.splash


import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.repository.SplashRepository
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewEvent
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class SplashViewModel(private val currentAppVersion: Int, initialState: SplashState) :
    RootViewModel<SplashState, SplashEvent>(initialState) {

    private val repository: SplashRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = SplashState.InProgress

            delay(500)

            execute { repository.getAppConfig() }.fold(
                error = { _uiState.value = SplashState.Error(it) },
                success = {
                    _uiState.value = if (currentAppVersion < it.latestVersion && it.isLatestVersionMandatory) {
                        SplashState.UpdateNeeded
                    } else {
                        SplashState.NoUpdateNeeded
                    }
                }
            )
        }
    }

    override fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.Attach -> attach()
        }
    }
}

sealed class SplashState : ViewState() {
    object InProgress : SplashState()
    class Error(val error: AppError) : SplashState()
    object UpdateNeeded : SplashState()
    object NoUpdateNeeded : SplashState()
}

sealed class SplashEvent : ViewEvent() {
    object Attach : SplashEvent()
}
