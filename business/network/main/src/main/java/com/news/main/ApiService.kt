package com.news.main

import com.news.data.models.NewsFeedNet
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("top-headlines")
    suspend fun getBusinessArticles(@QueryMap queryMap: Map<String, String>): NewsFeedNet

    @GET("everything")
    suspend fun getEverythingArticles(@QueryMap queryMap: Map<String, String>): NewsFeedNet
}