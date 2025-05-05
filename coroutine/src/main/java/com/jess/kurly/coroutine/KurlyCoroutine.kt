package com.jess.kurly.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface KurlyCoroutine {

    fun CoroutineScope.kurlyLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job
}

class KurlyCoroutineImpl : KurlyCoroutine {

    override fun CoroutineScope.kurlyLaunch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = launch(context, start) {
        val result = kotlin.runCatching {
            block()
        }.onFailure { exception ->
        }
    }
}
