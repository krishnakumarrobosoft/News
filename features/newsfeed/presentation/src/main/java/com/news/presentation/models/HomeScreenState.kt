package com.news.presentation.models

import com.news.models.Article
import com.news.state.State

sealed class HomeScreenState : State {

    data class Content(
        val toolbarState: ToolbarState,
        val sections: List<Section>,
        val errorType: DisplayErrorMessageType,
    ) : HomeScreenState()

    data object None : HomeScreenState()
}

sealed class DisplayErrorMessageType {
    data class Favorite(val errorMessage: String) : DisplayErrorMessageType()
    data class Generic(val errorMessage: String) : DisplayErrorMessageType()
    data object None : DisplayErrorMessageType()
}

data class Section(
    val items: List<List<Article>>,
    val title: String,
    val onFavorite: (Article) -> Unit,
    val onArticleClick: (Article) -> Unit,
    val isLoading: Boolean
)
