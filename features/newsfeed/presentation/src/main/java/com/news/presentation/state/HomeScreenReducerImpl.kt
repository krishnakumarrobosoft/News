package com.news.presentation.state

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.presentation.models.ToolbarState
import com.news.presentation.models.DisplayErrorMessageType
import com.news.presentation.models.Section
import com.news.presentation.models.HomeScreenState
import com.news.state.Reducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class HomeScreenReducer :
    Reducer<HomeScreenState, HomeScreenAction> {
    override val state = MutableStateFlow<HomeScreenState>(HomeScreenState.None)
}

class HomeScreenReducerImpl @Inject constructor(private val dispatcherProvider: DispatcherProvider) :
    HomeScreenReducer() {

    override suspend fun update(action: HomeScreenAction) {
        withContext(dispatcherProvider.default) {
            val newState: HomeScreenState = when (action) {
                is HomeScreenAction.SetContent -> HomeScreenState.Content(
                    toolbarState = ToolbarState.Content(
                        action.title,
                        action.backButtonResource,
                        action.refreshButtonResource,
                        onBackClick = action.onBackClick,
                        onRefreshClick = action.onRefreshClick,
                    ),
                    sections = listOf(
                        Section(
                           emptyList(),
                            title = "Top Headlines",
                            onArticleClick = action.onArticleClick,
                            onFavorite = action.onFavorite,
                            isLoading = true
                        ), Section(
                            emptyList(),
                            title = "Business",
                            onArticleClick = action.onArticleClick,
                            onFavorite = action.onFavorite,
                            isLoading = true
                        )
                    ),
                    errorType = DisplayErrorMessageType.None
                )

                is HomeScreenAction.DisplaySection -> displaySection(action)
                is HomeScreenAction.UpdateFavorite -> updateFavorites(action)
                is HomeScreenAction.SetError -> displayGenericError(action)
                is HomeScreenAction.DisplayFavoriteError -> displayFavoriteError(action)
            }
            state.value = newState
        }

    }

    private fun displayFavoriteError(action: HomeScreenAction.DisplayFavoriteError): HomeScreenState {
        return (state.value as? HomeScreenState.Content)?.let { state ->
            state.copy(errorType = DisplayErrorMessageType.Favorite(action.errorMessage))
        } ?: state.value
    }

    private fun displayGenericError(action: HomeScreenAction.SetError): HomeScreenState {
        return (state.value as? HomeScreenState.Content)?.let { state ->
            state.copy(errorType = DisplayErrorMessageType.Generic(action.error.message.orEmpty()))
        } ?: state.value
    }

    private fun updateFavorites(action: HomeScreenAction.UpdateFavorite): HomeScreenState {
        return (state.value as? HomeScreenState.Content)?.let { state ->
            return state.copy(
                sections = state.sections.map { section ->
                    section.copy(items = section.items.map { rowItems ->
                        rowItems.map { originalArticle ->
                            var modifiedArticle = originalArticle
                            if (action.article.title != null && originalArticle.title == action.article.title) {
                                modifiedArticle = originalArticle.copy(isFavorite = !action.article.isFavorite)
                            }
                            modifiedArticle
                        }
                    })
                },
                errorType = DisplayErrorMessageType.None
            )
        } ?: HomeScreenState.None
    }

    private fun displaySection(action: HomeScreenAction.DisplaySection): HomeScreenState {
        return (state.value as? HomeScreenState.Content)?.let { state ->
            state.copy(sections = state.sections.mapIndexed { index, section ->
                if (index == action.index) state.sections[index].copy(items = action.articles, isLoading = false)
                else state.sections[index]
            }, errorType = DisplayErrorMessageType.None)
        } ?: state.value
    }
}