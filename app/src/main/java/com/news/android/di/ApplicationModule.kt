package com.news.android.di

import com.news.android.appinfo.AppInfoProviderImpl
import com.news.appinfo.AppInfoProvider
import com.news.di.GlobalNavigationStateModule
import com.news.di.KotlinDiModule
import com.news.di.NewsFeedPresentationModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [KotlinDiModule::class, NewsFeedPresentationModule::class, GlobalNavigationStateModule::class]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideAppInfoProvider(): AppInfoProvider {
        return AppInfoProviderImpl()
    }
}