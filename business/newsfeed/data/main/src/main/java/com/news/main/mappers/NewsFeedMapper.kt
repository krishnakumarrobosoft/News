package com.news.main.mappers

import com.news.data.models.NewsFeedNet
import com.news.kotlin.mapper.Mapper
import com.news.models.NewsFeed
import javax.inject.Inject

class NewsFeedMapper @Inject constructor(private val articlesMapper: ArticlesMapper) : Mapper<NewsFeedNet, NewsFeed> {

    override fun map(param: NewsFeedNet): NewsFeed {
        return NewsFeed(status = param.status,
            totalResults = param.totalResults,
            articles = param.articles.map {
                articlesMapper.map(it)
            })
    }
}