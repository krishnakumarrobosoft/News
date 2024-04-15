package com.news.main

import com.news.data.models.ArticlesNet
import com.news.data.models.SourceNet
import com.news.main.mappers.ArticlesMapper
import com.news.main.mappers.SourceMapper
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class ArticlesMapperTest {

    private val sourceMapper: SourceMapper = mockk(relaxed = true)
    private val sut = ArticlesMapper(sourceMapper)

    private val articlesNetworkData =
        ArticlesNet(
            source = SourceNet("1", "test"),
            author = "test",
            description = "test"
        )

    @Test
    fun `Given ArticlesNet object When map method called the Articles object returned`() {

        // When
        val result = sut.map(articlesNetworkData)

        // Then
        assertEquals(result.author, articlesNetworkData.author)
        assertEquals(result.description, articlesNetworkData.description)
    }
}