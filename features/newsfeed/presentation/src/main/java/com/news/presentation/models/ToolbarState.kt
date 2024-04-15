package com.news.presentation.models

import androidx.annotation.DrawableRes
import com.news.state.State

sealed class ToolbarState : State {
    data object None : ToolbarState()

    data class Content(
        val title: String,
        @DrawableRes val backButtonResource: Int? = null,
        @DrawableRes val refreshButtonResource: Int? = null,
        val onBackClick: (() -> Unit)? = null,
        val onRefreshClick: (() -> Unit)? = null
    ) : ToolbarState()
}

