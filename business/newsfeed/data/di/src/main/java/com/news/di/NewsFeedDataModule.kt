package com.news.di

import android.content.Context
import android.content.SharedPreferences
import com.news.main.mappers.NewsFeedMapper
import com.news.main.mediator.NewsFeedMediatorDataSource
import com.news.main.repositories.NewsFeedRepository
import com.news.main.repositories.NewsFeedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsFeedDataModule {
//abstract class NewsFeedDataModule {

//    @Binds
//    abstract fun bindAccountRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    @Provides
    @Singleton
    fun provideNewsFeedRepositoryImpl(
        newsFeedMapper: NewsFeedMapper,
        newsFeedMediatorDataSource: NewsFeedMediatorDataSource
    ): NewsFeedRepository {
        return NewsFeedRepositoryImpl(newsFeedMapper, newsFeedMediatorDataSource)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("news_app_preference", Context.MODE_PRIVATE)
    }

}