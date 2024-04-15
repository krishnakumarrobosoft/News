package com.news.main.repositories

import com.news.models.NewsFeed

interface NewsFeedRepository {

    suspend fun getBusinessArticles(queryMap:Map<String, String>): NewsFeed

    suspend fun getEverythingArticles(queryMap:Map<String, String>): NewsFeed

    suspend fun getFavoriteItems():List<String>

    suspend fun addToFavorite(id:String)

    suspend fun removeFromFavorite(id:String)
}