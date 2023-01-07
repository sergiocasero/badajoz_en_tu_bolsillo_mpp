package com.badajoz.badajozentubolsillo.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

actual open class CFlow<T> actual constructor(
    private val flow: Flow<T>
) : Flow<T> by flow {

    fun subscribe(
        coroutineScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job: Job = coroutineScope.launch(dispatcher) {
            flow.onEach { onCollect(it) }.collect()
        }
        return DisposableHandle {
            job.cancel()
        }
    }

    fun subscribe(onCollect: (T) -> Unit): DisposableHandle {
        @Suppress("OPT_IN_USAGE")
        return subscribe(
            coroutineScope = GlobalScope,
            dispatcher = Dispatchers.Main,
            onCollect = onCollect
        )
    }
}