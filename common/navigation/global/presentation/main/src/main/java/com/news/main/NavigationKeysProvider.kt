package com.news.main

import com.news.models.GlobalNavigationState
import javax.inject.Inject

class NavigationKeysProvider @Inject constructor() {
    val keys: List<GlobalNavigationState> = listOf(
        GlobalNavigationState.Home(),
        GlobalNavigationState.None(),
        GlobalNavigationState.ArticleDetail()
    )
}