package com.news.models

data class NewsFeed(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article> = arrayListOf()
)
