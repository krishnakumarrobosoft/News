package com.news.main.reducers

import com.news.main.action.GlobalNavigationAction
import com.news.models.GlobalNavigationState
import com.news.state.Reducer

interface GlobalNavigationReducer : Reducer<GlobalNavigationState, GlobalNavigationAction>
