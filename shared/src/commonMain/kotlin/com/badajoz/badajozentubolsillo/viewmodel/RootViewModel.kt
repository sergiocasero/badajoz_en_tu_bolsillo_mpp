package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.datasource.AppConfig
import com.badajoz.flow.CStateFlow
import com.badajoz.flow.cStateFlow
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.utils.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class RootViewModel<S, E>(initialState: S)
    : KoinComponent, PlatformViewModel() {

    private val job = SupervisorJob()

    private val executor: Executor by inject()

    protected val appConfig: AppConfig by inject()
    protected val vmScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    protected val _uiState = MutableStateFlow(initialState)
    val state: CStateFlow<S> = _uiState.cStateFlow()

    abstract fun attach(): RootViewModel<S, E>

    override fun detach() {
        super.detach()
        job.cancelChildren()
    }

    abstract fun onEvent(event: E)

    protected suspend fun <T> execute(f: suspend () -> Either<AppError, T>): Either<AppError, T> =
        withContext(executor.bg) { f() }



    fun <T> Flow<T>.observe(onChange: ((T) -> Unit)) {
        onEach {
            onChange(it)
        }.launchIn(
            vmScope
        )
    }
}

open class ViewState