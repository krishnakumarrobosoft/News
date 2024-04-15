package com.news.main.interceptors

import com.news.appinfo.AppInfoProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsApiInterceptor @Inject constructor(private val appInfo: AppInfoProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder().addQueryParameter(API_KEY_QUERY, appInfo.apiKey).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private companion object {
        private const val API_KEY_QUERY = "apiKey"
    }
}