package com.news.presentation.state

import androidx.annotation.DrawableRes
import com.news.state.Action

sealed class ToolbarAction:Action {

    data class DisplayToolbar(
        val title: String,
        @DrawableRes val backButtonResource: Int? = null,
        @DrawableRes val refreshButtonResource: Int? = null,
        val onBackClick: () -> Unit,
        val onRefreshClick: () -> Unit
    )
}