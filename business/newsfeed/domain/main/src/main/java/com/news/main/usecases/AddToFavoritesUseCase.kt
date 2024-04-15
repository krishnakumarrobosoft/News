package com.news.main.usecases

import com.news.main.repositories.NewsFeedRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val newsFeedRepository: NewsFeedRepository) {

    suspend fun addToFavorites(id: String) {
        return newsFeedRepository.addToFavorite(id)
    }
}