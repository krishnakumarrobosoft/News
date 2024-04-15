package com.news.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.action.GlobalNavigationAction
import com.news.main.reducers.GlobalNavigationReducer
import com.news.models.GlobalNavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlobalNavigationViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val reducer: GlobalNavigationReducer,
    navigationKeysProvider: NavigationKeysProvider
) : ViewModel() {
    val state = reducer.state
    val keys = navigationKeysProvider.keys

    init {
        viewModelScope.launch(dispatcherProvider.main) {
            reducer.update(
                GlobalNavigationAction.GoTo(GlobalNavigationState.Home())
            )
        }
    }
}