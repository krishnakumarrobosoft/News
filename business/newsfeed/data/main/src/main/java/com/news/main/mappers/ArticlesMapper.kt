package com.news.main.mappers

import com.news.data.models.ArticlesNet
import com.news.kotlin.mapper.Mapper
import com.news.models.Article
import java.util.UUID
import javax.inject.Inject

class ArticlesMapper @Inject constructor(private val sourceMapper: SourceMapper) : Mapper<ArticlesNet, Article> {
    override fun map(param: ArticlesNet): Article {
        return Article(
            source = param.source?.let { sourceMapper.map(it) },
            author = param.author,
            title = param.title,
            description = param.description,
            url = param.url,
            urlToImage = param.urlToImage,
            publishedAt = param.publishedAt,
            content = param.content
        )
    }
}