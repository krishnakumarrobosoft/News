package com.news.di

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.reducers.GlobalNavigationReducer
import com.news.main.reducers.GlobalNavigationReducerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalNavigationStateModule {

    @Provides
    @Singleton
    fun provideGlobalNavigationReducer(dispatcherProvider: DispatcherProvider): GlobalNavigationReducer {
        return GlobalNavigationReducerImpl(dispatcherProvider)
    }
}