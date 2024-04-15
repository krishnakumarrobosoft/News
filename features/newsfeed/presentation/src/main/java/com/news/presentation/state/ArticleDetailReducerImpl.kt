package com.news.presentation.state

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.presentation.models.ArticleDetailState
import com.news.presentation.models.ToolbarState
import com.news.state.Reducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class ArticleDetailReducer :
    Reducer<ArticleDetailState, ArticleDetailAction> {
    override val state = MutableStateFlow<ArticleDetailState>(ArticleDetailState.None)
}

class ArticleDetailReducerImpl @Inject constructor(private val dispatcherProvider: DispatcherProvider) : ArticleDetailReducer() {

    override suspend fun update(action: ArticleDetailAction) {
        withContext(dispatcherProvider.default) {
            val newState: ArticleDetailState = when (action) {
                is ArticleDetailAction.SetContent -> ArticleDetailState.Content(
                    article = action.article,
                    toolbarState = ToolbarState.Content(
                        action.article.title.orEmpty(),
                        action.backButtonResource,
                        onBackClick = action.onBackClick
                    ),
                    displayErrorMessage = action.displayErrorMessage,
                    displayErrorMessageType = action.displayErrorMessageType,
                    onFavoriteClick = action.onFavoriteClick
                )

                is ArticleDetailAction.DisplayError -> updateContentErrorState(action)

                is ArticleDetailAction.UpdateFavorite -> updateFavorites(action)
            }
            state.value = newState
        }

    }

    private fun updateContentErrorState(action: ArticleDetailAction.DisplayError): ArticleDetailState {
        return (state.value as? ArticleDetailState.Content)?.copy(displayErrorMessageType = action.errorMessageType)
            ?: ArticleDetailState.None
    }

    private fun updateFavorites(action: ArticleDetailAction.UpdateFavorite): ArticleDetailState {
        return (state.value as? ArticleDetailState.Content)?.let { state ->
            return state.copy(
                article = state.article.copy(isFavorite = !action.article.isFavorite),
            )
        } ?: ArticleDetailState.None
    }
}