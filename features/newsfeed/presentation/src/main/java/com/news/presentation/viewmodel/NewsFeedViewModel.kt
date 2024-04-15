package com.news.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.action.GlobalNavigationAction
import com.news.main.reducers.GlobalNavigationReducer
import com.news.main.usecases.AddToFavoritesUseCase
import com.news.main.usecases.GetEverythingArticlesUseCase
import com.news.main.usecases.GetFavoritesUseCase
import com.news.main.usecases.GetTopHeadlinesUseCase
import com.news.main.usecases.RemoveFromFavoritesUseCase
import com.news.models.Article
import com.news.models.GlobalNavigationState
import com.news.presentation.models.HomeScreenState
import com.news.presentation.state.HomeScreenAction
import com.news.presentation.state.HomeScreenReducer
import com.news.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val homeScreenReducer: HomeScreenReducer,
    private val globalNavigationReducer: GlobalNavigationReducer,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val getEverythingArticlesUseCase: GetEverythingArticlesUseCase,
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    val homeScreenState: StateFlow<HomeScreenState> = homeScreenReducer.state

    init {
        setup()
        loadTopSectionData()
        loadBusinessSectionData()
    }

    fun loadData() {
        loadTopSectionData()
        loadBusinessSectionData()
    }

    private fun setup() {
        viewModelScope.launch(dispatcherProvider.io) {
            homeScreenReducer.update(
                HomeScreenAction.SetContent(
                    "News",
                    refreshButtonResource = R.drawable.ic_refresh,
                    onBackClick = {

                    },
                    onRefreshClick = {
                        loadTopSectionData()
                        loadBusinessSectionData()
                    },
                    onArticleClick = { article ->
                        navigateToArticleDetailPage(article)
                    },
                    onFavorite = { article ->
                        updateFavorites(article)
                        viewModelScope.launch(dispatcherProvider.main) {
                            homeScreenReducer.update(HomeScreenAction.UpdateFavorite(article))
                        }
                    }
                )
            )
        }
    }

    private fun loadTopSectionData() {
        viewModelScope.launch(dispatcherProvider.io) {
            val topSectionItems = mutableListOf<List<Article>>()
            supervisorScope {
                val item1 = async {
                    getEverythingArticlesUseCase.getEverythingArticles(
                        mapOf(
                            "q" to "tesla",
                            "from" to "2024-03-28",
                            "sortBy" to "publishedAt"
                        )
                    )
                }
                val item2 = async { getTopHeadlinesUseCase.getTopHeadlinesArticles(mapOf("sources" to "techcrunch")) }
                val item3 = async {
                    getEverythingArticlesUseCase.getEverythingArticles(mapOf("domains" to "wsj.com"))
                }
                try {
                    topSectionItems.add(appendFavoriteState(item1.await().articles))
                } catch (e: Exception) {
                    Log.d("newslog", "got the error item1 $e")
                }
                try {
                    topSectionItems.add(appendFavoriteState(item2.await().articles))
                } catch (e: Exception) {
                    Log.d("newslog", "got the error item2 $e")
                }
                try {
                    topSectionItems.add(appendFavoriteState(item3.await().articles))
                } catch (e: Exception) {
                    Log.d("newslog", "got the error item3 $e")
                }
                homeScreenReducer.update(HomeScreenAction.DisplaySection(0, "Top Headlines", topSectionItems.toList()))
            }
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
                }
            } catch (e: Exception) {
                homeScreenReducer.update(HomeScreenAction.DisplayFavoriteError("Something went wrong"))
            }
        }
    }

    private suspend fun appendFavoriteState(articleList1: List<Article>): List<Article> {
        return articleList1.map { article ->
            var modifiedArticle = article
            if (getFavoritesUseCase.getFavoritesUseCase().contains(article.title)) {
                modifiedArticle = modifiedArticle.copy(isFavorite = true)
            }
            modifiedArticle
        }
    }

    private fun loadBusinessSectionData() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val result = getTopHeadlinesUseCase.getTopHeadlinesArticles(mapOf("country" to "us", "category" to "business"))
                Log.d("newslog", "got the result $result")
                homeScreenReducer.update(
                    HomeScreenAction.DisplaySection(
                        1,
                        "Business",
                        listOf(appendFavoriteState(result.articles))
                    )
                )
            } catch (e: Exception) {
                Log.d("newslog", "got the error $e")
                homeScreenReducer.update(HomeScreenAction.SetError(e))
            }
        }
    }

    private fun navigateToArticleDetailPage(article: Article) {
        viewModelScope.launch(dispatcherProvider.main) {
            globalNavigationReducer.update(
                GlobalNavigationAction.GoTo(
                    GlobalNavigationState.ArticleDetail(article = article)
                )
            )
        }
    }
}