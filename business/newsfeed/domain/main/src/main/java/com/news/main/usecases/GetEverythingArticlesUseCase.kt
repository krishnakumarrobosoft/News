package com.news.main.usecases

import com.news.main.repositories.NewsFeedRepository
import com.news.models.NewsFeed
import javax.inject.Inject

class GetEverythingArticlesUseCase @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository
) {

    suspend fun getEverythingArticles(queryMap:Map<String, String>): NewsFeed {
        return newsFeedRepository.getEverythingArticles(queryMap)
    }
}