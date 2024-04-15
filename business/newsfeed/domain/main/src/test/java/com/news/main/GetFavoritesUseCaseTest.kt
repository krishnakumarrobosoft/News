package com.news.main

import com.news.main.repositories.NewsFeedRepository
import com.news.main.usecases.GetFavoritesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoritesUseCaseTest {

    private val repository = mockk<NewsFeedRepository>(relaxed = true)
    private val sut = GetFavoritesUseCase(repository)

    @Test
    fun `Given favorite items present When getFavoritesUseCase method invoked Then favorite list returned`() {
        runTest {
            // Given
            val favoriteItems = listOf("123", "456")
            coEvery { repository.getFavoriteItems() } returns favoriteItems

            // When
            val result = sut.getFavoritesUseCase()

            // Then
            assertEquals(favoriteItems, result)
        }
    }
}