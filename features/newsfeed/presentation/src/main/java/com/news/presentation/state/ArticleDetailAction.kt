package com.news.presentation.state

import androidx.annotation.DrawableRes
import com.news.models.Article
import com.news.state.Action

sealed class ArticleDetailAction : Action {

    data class SetContent(
        val article: Article,
        @DrawableRes val backButtonResource: Int? = null,
        val onBackClick: () -> Unit,
        val onFavoriteClick: (Article) -> Unit,
        val displayErrorMessage: (displayErrorMessageType: DisplayErrorMessageType) -> Unit,
        val displayErrorMessageType: DisplayErrorMessageType
    ) : ArticleDetailAction()

    data class UpdateFavorite(val article: Article) : ArticleDetailAction()

    data class DisplayError(val errorMessageType: DisplayErrorMessageType) : ArticleDetailAction()

    sealed class DisplayErrorMessageType {
        data class Favorites(val message: String) : DisplayErrorMessageType()
        data object None : DisplayErrorMessageType()
    }

}