package com.news.presentation.state

import androidx.annotation.DrawableRes
import com.news.models.Article
import com.news.state.Action

sealed class HomeScreenAction : Action {

    data class SetContent(
        val title: String,
        @DrawableRes val backButtonResource: Int? = null,
        @DrawableRes val refreshButtonResource: Int? = null,
        val onBackClick: () -> Unit,
        val onRefreshClick: () -> Unit,
        val onFavorite: (Article) -> Unit,
        val onArticleClick: (Article) -> Unit,
    ) : HomeScreenAction()

    data class DisplaySection(val index: Int, val title: String, val articles: List<List<Article>>) : HomeScreenAction()
    data class UpdateFavorite(val article: Article) : HomeScreenAction()
    data class DisplayFavoriteError(val errorMessage:String): HomeScreenAction()
    data class SetError(val error: Throwable) : HomeScreenAction()
}
