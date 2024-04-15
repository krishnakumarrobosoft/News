package com.news.main

import com.news.data.models.ArticlesNet
import com.news.data.models.NewsFeedNet
import com.news.data.models.SourceNet
import com.news.main.mappers.NewsFeedMapper
import com.news.main.mediator.NewsFeedMediatorDataSource
import com.news.main.repositories.NewsFeedRepositoryImpl
import com.news.models.NewsFeed
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class NewsFeedRepositoryImplTest {

    private val newsFeedMapper: NewsFeedMapper = mockk(relaxed = true)
    private val newsFeedMediatorDataSource: NewsFeedMediatorDataSource = mockk(relaxed = true)
    private val sut = NewsFeedRepositoryImpl(newsFeedMapper, newsFeedMediatorDataSource)

    @Test
    fun `Given getBusinessArticles from data source returns success When getBusinessArticles method called Then NewsFeed result returned`() {

        runTest {

            // Given
            val query = mapOf("test" to "test")
            val newsFeed = mockk<NewsFeed>(relaxed = true)
            coEvery { newsFeedMediatorDataSource.getBusinessArticles(query) } returns mockk(relaxed = true)
            every { newsFeedMapper.map(any()) } returns newsFeed

            // When
            val result = sut.getBusinessArticles(query)

            // Then
            assertEquals(result, newsFeed)
        }
    }

    @Test
    fun `Given getEverythingArticles from data source returns success When getEverythingArticles method called Then NewsFeed result returned`() {

        runTest {

            // Given
            val query = mapOf("test" to "test")
            val newsFeed = mockk<NewsFeed>(relaxed = true)
            coEvery { newsFeedMediatorDataSource.getEverythingArticles(query) } returns mockk(relaxed = true)
            every { newsFeedMapper.map(any()) } returns newsFeed

            // When
            val result = sut.getEverythingArticles(query)

            // Then
            assertEquals(result, newsFeed)
        }
    }

    @Test
    fun `Given favorite items in data source exists When getFavoriteItems method called Then favorite list returned`() {

        runTest {

            // Given
            val favoriteList = listOf("1", "2")
            coEvery { newsFeedMediatorDataSource.getFavoriteItems() } returns favoriteList

            // When
            val result = sut.getFavoriteItems()

            // Then
            assertEquals(favoriteList, result)
        }
    }

}