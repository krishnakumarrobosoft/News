package com.news.main.mediator

import com.news.data.models.NewsFeedNet
import com.news.main.NewsPersistanceStore
import com.news.main.network.NewsFeedDataSource
import javax.inject.Inject

class NewsFeedMediatorDataSource @Inject constructor(
    private val newsFeedDataSource: NewsFeedDataSource,
    private val newsPersistanceStore: NewsPersistanceStore
) {

    suspend fun getBusinessArticles(queryMap: Map<String, String>): NewsFeedNet {
        return newsFeedDataSource.getBusinessArticles(queryMap)
    }

    suspend fun getEverythingArticles(queryMap: Map<String, String>): NewsFeedNet {
        return newsFeedDataSource.getEverythingArticles(queryMap)
    }

    suspend fun saveFavoriteItem(id: String) {
        val favoriteList = newsPersistanceStore.getStringList(KEY_FAVORITE_PREFERENCE).toMutableList()
        favoriteList.add(id)
        newsPersistanceStore.saveStringList(KEY_FAVORITE_PREFERENCE, favoriteList)
    }

    suspend fun removeFavoriteItem(id: String) {
        val favoriteList = newsPersistanceStore.getStringList(KEY_FAVORITE_PREFERENCE).toMutableList()
        if (favoriteList.contains(id)) {
            favoriteList.remove(id)
            newsPersistanceStore.saveStringList(KEY_FAVORITE_PREFERENCE, favoriteList)
        }
    }

    suspend fun getFavoriteItems(): List<String> {
        return newsPersistanceStore.getStringList(KEY_FAVORITE_PREFERENCE)
    }

    private companion object {
        private const val KEY_FAVORITE_PREFERENCE = "keyFavoritePreference"
    }
}