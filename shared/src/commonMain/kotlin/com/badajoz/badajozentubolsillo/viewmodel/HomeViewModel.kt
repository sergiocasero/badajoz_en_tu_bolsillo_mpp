package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.utils.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(executor: Executor) {

    private val job = SupervisorJob()

    private val scope = CoroutineScope(job)
    private val _uiState = MutableStateFlow<HomeState>(HomeState.InProgress)

    val state: StateFlow<HomeState> = _uiState

    fun attach() {
        scope.launch {
            _uiState.value = HomeState.InProgress


        }
    }

    fun detach() {
        scope.cancel()
    }
}

sealed class HomeState {
    object InProgress : HomeState()
    class Error(val error: Error) : HomeState()
}