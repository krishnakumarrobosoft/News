package com.news.main.reducers

import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.action.GlobalNavigationAction
import com.news.models.GlobalNavigationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class GlobalNavigationReducerImpl(
    private val dispatcherProvider: DispatcherProvider,
) : GlobalNavigationReducer {
    override val state = MutableStateFlow<GlobalNavigationState>(GlobalNavigationState.None())
    private var idInt = 0

    override suspend fun update(action: GlobalNavigationAction) {
        withContext(dispatcherProvider.default) {
            state.value = when (action) {
                is GlobalNavigationAction.GoBack -> GlobalNavigationState.GoBack(shouldRefresh = action.shouldRefresh)
                is GlobalNavigationAction.GoTo -> resolveGotoAction(action.screen)
            }
        }
    }

    private fun resolveGotoAction(screen: GlobalNavigationState): GlobalNavigationState {
        return when (screen) {
            is GlobalNavigationState.GoBack -> screen.copy(id = ++idInt)
            is GlobalNavigationState.Home -> screen.copy(id = ++idInt)
            is GlobalNavigationState.None -> screen.copy(id = ++idInt)
            is GlobalNavigationState.ArticleDetail -> screen.copy(id = ++idInt)
        }
    }
}