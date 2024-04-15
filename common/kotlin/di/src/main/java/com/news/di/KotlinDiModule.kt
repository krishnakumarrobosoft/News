package com.news.di

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.kotlin.coroutines.main.CoroutineDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//object KotlinDiModule {
//
//    @Provides
//    fun dispatcherProvider(): DispatcherProvider {
//        return CoroutineDispatcherProvider()
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class KotlinDiModule {

    @Binds
    abstract fun dispatcherProvider(impl: CoroutineDispatcherProvider): DispatcherProvider
}