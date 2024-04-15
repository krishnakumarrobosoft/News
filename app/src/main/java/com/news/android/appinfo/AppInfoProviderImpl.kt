package com.news.android.appinfo

import com.news.android.BuildConfig
import com.news.appinfo.AppInfoProvider

class AppInfoProviderImpl : AppInfoProvider {
    override val baseUrl = BuildConfig.BASE_URL
    override val apiKey = BuildConfig.API_KEY
}