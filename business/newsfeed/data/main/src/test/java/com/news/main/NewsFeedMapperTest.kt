package com.news.main

import com.news.data.models.ArticlesNet
import com.news.data.models.NewsFeedNet
import com.news.data.models.SourceNet
import com.news.main.mappers.ArticlesMapper
import com.news.main.mappers.NewsFeedMapper
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class NewsFeedMapperTest {

    private val articleMapper:ArticlesMapper = mockk(relaxed=true)
    private val sut = NewsFeedMapper(articleMapper)

    private val newFeedNetworkData = NewsFeedNet(
        status = "success",
        totalResults = 10,
        articles = arrayListOf(
            ArticlesNet(
                source = SourceNet("1", "test"),
                author = "test",
                description = "test"
            )
        )
    )

    @Test
    fun `Given NewsFeedNet object When map method called the NewsFeed object returned`(){

        // When
        val result = sut.map(newFeedNetworkData)

        // Then
        assertEquals(result.status, newFeedNetworkData.status)
        assertEquals(result.totalResults, newFeedNetworkData.totalResults)
    }
}