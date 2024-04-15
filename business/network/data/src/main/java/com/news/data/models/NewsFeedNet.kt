package com.news.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsFeedNet(
    @SerialName("status")
    val status: String? = null,

    @SerialName("totalResults")
    val totalResults: Int? = null,

    @SerialName("articles")
    val articles: ArrayList<ArticlesNet> = arrayListOf()
)