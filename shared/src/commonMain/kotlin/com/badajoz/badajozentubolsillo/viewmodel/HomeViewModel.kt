package com.badajoz.badajozentubolsillo.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel(initialState: HomeState) : RootViewModel<HomeState, HomeEvent, HomeActions>(initialState) {

    private val job = SupervisorJob()

    private val scope = CoroutineScope(job)

    override fun attach() = apply {
        scope.launch {
            _uiState.value = HomeState.InProgress


        }
    }

    override fun detach() {
        scope.cancel()
    }

    override fun onEvent(event: HomeEvent) {
        TODO("Not yet implemented")
    }
}

sealed class HomeState : ViewState() {
    object InProgress : HomeState()
    class Error(val error: Error) : HomeState()
}

sealed class HomeEvent {

}

sealed class HomeActions {

}