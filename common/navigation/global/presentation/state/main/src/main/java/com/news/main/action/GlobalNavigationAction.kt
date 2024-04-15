package com.news.main.action

import com.news.models.GlobalNavigationState
import com.news.state.Action

sealed class GlobalNavigationAction : Action {
    data class GoTo(
        val screen: GlobalNavigationState,
    ) : GlobalNavigationAction()

    data class GoBack(val shouldRefresh: Boolean = false) : GlobalNavigationAction()
}
