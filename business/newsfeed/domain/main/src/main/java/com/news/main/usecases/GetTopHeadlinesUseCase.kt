package com.news.main.usecases

import com.news.main.repositories.NewsFeedRepository
import com.news.models.NewsFeed
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val newsFeedRepository: NewsFeedRepository
) {

    suspend fun getTopHeadlinesArticles(queryMap:Map<String, String>): NewsFeed {
        return newsFeedRepository.getBusinessArticles(queryMap)
    }
}