package com.news.kotlin.coroutines.main

import com.news.kotlin.coroutines.api.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineDispatcherProvider @Inject constructor() : DispatcherProvider {
    override val main: CoroutineContext get() = Dispatchers.Main
    override val default: CoroutineContext get() = Dispatchers.Default
    override val io: CoroutineContext get() = Dispatchers.IO
    override val unconfined: CoroutineContext get() = Dispatchers.Unconfined
}
