package com.news.main.usecases

import com.news.main.repositories.NewsFeedRepository
import com.news.models.Article
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val newsFeedRepository: NewsFeedRepository) {

    suspend fun getFavoritesUseCase(): List<String> {
        return newsFeedRepository.getFavoriteItems()
    }
}