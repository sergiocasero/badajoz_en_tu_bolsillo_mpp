package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.flow.CFlow
import com.badajoz.badajozentubolsillo.flow.CStateFlow
import com.badajoz.badajozentubolsillo.flow.cFlow
import com.badajoz.badajozentubolsillo.flow.cStateFlow
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.utils.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class RootViewModel<S, E, A>(initialState: S) : KoinComponent, PlatformViewModel() {

    private val job = SupervisorJob()

    private val executor: Executor by inject()
    protected val vmScope: CoroutineScope get() = CoroutineScope(job + executor.main)

    protected val _uiState = MutableStateFlow(initialState)
    val state: CStateFlow<S> = _uiState.cStateFlow()

    protected val _actions = Channel<A>(Channel.BUFFERED)
    val actions: CFlow<A> = _actions.receiveAsFlow().cFlow()

    abstract fun attach(): RootViewModel<S, E, A>

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