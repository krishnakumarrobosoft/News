package com.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.action.GlobalNavigationAction
import com.news.main.reducers.GlobalNavigationReducer
import com.news.main.usecases.AddToFavoritesUseCase
import com.news.main.usecases.RemoveFromFavoritesUseCase
import com.news.models.Article
import com.news.presentation.state.ArticleDetailAction
import com.news.presentation.state.ArticleDetailReducer
import com.news.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val articleDetailReducer: ArticleDetailReducer,
    private val globalNavigationReducer: GlobalNavigationReducer,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {
    val articleDetailState = articleDetailReducer.state
    private var isFavoriteStateChanged = false

    fun setup(article: Article) {
        viewModelScope.launch(dispatcherProvider.io) {
            articleDetailReducer.update(
                ArticleDetailAction.SetContent(
                    article,
                    backButtonResource = R.drawable.ic_nav_icon_chevron_left,
                    onBackClick = ::onBack,
                    onFavoriteClick = { article ->
                        updateFavorites(article)
                    },
                    displayErrorMessage = ::displayErrorMessage,
                    displayErrorMessageType = ArticleDetailAction.DisplayErrorMessageType.None
                )
            )
        }
    }

    fun onBack() {
        viewModelScope.launch(dispatcherProvider.main) {
            globalNavigationReducer.update(GlobalNavigationAction.GoBack(isFavoriteStateChanged))
        }
    }

    private fun updateFavorites(article: Article) {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                article.title?.let { id ->
                    if (article.isFavorite) {
                        removeFromFavoritesUseCase.removeFromFavorites(id)
                    } else {
                        addToFavoritesUseCase.addToFavorites(id)
                    }
                    articleDetailReducer.update(ArticleDetailAction.UpdateFavorite(article))
                }
                isFavoriteStateChanged = true
            } catch (e: Exception) {
                displayErrorMessage(ArticleDetailAction.DisplayErrorMessageType.Favorites("Something went wrong"))
            }
        }
    }

    private fun displayErrorMessage(displayErrorMessageType: ArticleDetailAction.DisplayErrorMessageType) {
        viewModelScope.launch(dispatcherProvider.main) {
            articleDetailReducer.update(
                ArticleDetailAction.DisplayError(displayErrorMessageType)
            )
        }
    }

}