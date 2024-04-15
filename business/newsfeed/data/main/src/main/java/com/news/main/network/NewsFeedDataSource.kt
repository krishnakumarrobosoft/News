package com.news.main.network

import com.news.data.models.NewsFeedNet
import com.news.main.ApiService
import javax.inject.Inject

class NewsFeedDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getBusinessArticles(queryMap: Map<String, String>): NewsFeedNet {
        return apiService.getBusinessArticles(queryMap)
    }

    suspend fun getEverythingArticles(queryMap: Map<String, String>): NewsFeedNet {
        return apiService.getEverythingArticles(queryMap)
    }
}