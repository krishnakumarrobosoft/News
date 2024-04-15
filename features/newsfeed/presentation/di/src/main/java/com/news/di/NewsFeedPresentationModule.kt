package com.news.di

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.presentation.state.ArticleDetailReducer
import com.news.presentation.state.ArticleDetailReducerImpl
import com.news.presentation.state.HomeScreenReducer
import com.news.presentation.state.HomeScreenReducerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewsFeedPresentationModule {

    @Provides
    fun provideHomeScreenReducer(dispatcherProvider: DispatcherProvider): HomeScreenReducer {
        return HomeScreenReducerImpl(dispatcherProvider)
    }

    @Provides
    fun provideArticleDetailReducer(dispatcherProvider: DispatcherProvider): ArticleDetailReducer {
        return ArticleDetailReducerImpl(dispatcherProvider)
    }
}