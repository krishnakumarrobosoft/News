package com.news.main.repositories

import com.news.main.mappers.NewsFeedMapper
import com.news.main.mediator.NewsFeedMediatorDataSource
import com.news.models.NewsFeed
import javax.inject.Inject

class NewsFeedRepositoryImpl @Inject constructor(
    private val newsFeedMapper: NewsFeedMapper,
    private val newsFeedMediatorDataSource: NewsFeedMediatorDataSource
) : NewsFeedRepository {
    override suspend fun getBusinessArticles(queryMap: Map<String, String>): NewsFeed {
        return newsFeedMapper.map(newsFeedMediatorDataSource.getBusinessArticles(queryMap))
    }

    override suspend fun getEverythingArticles(queryMap: Map<String, String>): NewsFeed {
        return newsFeedMapper.map(newsFeedMediatorDataSource.getEverythingArticles(queryMap))
    }

    override suspend fun getFavoriteItems(): List<String> {
        return newsFeedMediatorDataSource.getFavoriteItems()
    }

    override suspend fun addToFavorite(id: String) {
        newsFeedMediatorDataSource.saveFavoriteItem(id)
    }

    override suspend fun removeFromFavorite(id: String) {
        newsFeedMediatorDataSource.removeFavoriteItem(id)
    }
}