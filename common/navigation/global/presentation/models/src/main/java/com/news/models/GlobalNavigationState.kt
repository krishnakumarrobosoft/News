package com.news.models

import com.news.api.NavigationKey
import com.news.models.keys.ARTICLE_DETAIL
import com.news.models.keys.BACK
import com.news.models.keys.HOME
import com.news.models.keys.NONE
import com.news.state.State

sealed class GlobalNavigationState(open val id: Int = 0) : State, NavigationKey {

    data class None(override val id: Int = 0, override val key: String = NONE) : GlobalNavigationState()

    data class Home(override val id: Int = 0, override val key: String = HOME) : GlobalNavigationState()

    data class ArticleDetail(
        override val id: Int = 0,
        override val key: String = ARTICLE_DETAIL,
        val article: Article? = null
    ) : GlobalNavigationState()

    data class GoBack(
        override val id: Int = 0,
        override val key: String = BACK,
        val shouldRefresh:Boolean = false
    ) : GlobalNavigationState()

}