package com.news.presentation.models

import com.news.models.Article
import com.news.presentation.state.ArticleDetailAction
import com.news.state.State

sealed class ArticleDetailState : State {

    data class Content(
        val article: Article,
        val toolbarState: ToolbarState,
        val onFavoriteClick: (Article) -> Unit,
        val displayErrorMessage: (displayErrorMessageType: ArticleDetailAction.DisplayErrorMessageType) -> Unit,
        val displayErrorMessageType: ArticleDetailAction.DisplayErrorMessageType
        ) : ArticleDetailState()


    data object None : ArticleDetailState()
}