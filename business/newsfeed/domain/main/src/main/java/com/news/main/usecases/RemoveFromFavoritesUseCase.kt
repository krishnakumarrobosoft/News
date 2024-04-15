package com.news.main.usecases

import com.news.main.repositories.NewsFeedRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(private val newsFeedRepository: NewsFeedRepository) {

    suspend fun removeFromFavorites(id:String) {
        return newsFeedRepository.removeFromFavorite(id)
    }
}