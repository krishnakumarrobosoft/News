package com.news.kotlin.coroutines.api

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
    val unconfined: CoroutineContext
}
