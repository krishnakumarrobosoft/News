package com.news.ui.compositions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.news.presentation.models.ToolbarState

@Composable
fun StandardToolbar(
    state: ToolbarState.Content,
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            state.backButtonResource?.let { backButtonResource ->
                IconButton(
                    modifier = Modifier
                        .padding(start = 2.dp),
                    onClick = { state.onBackClick?.invoke() }
                ) {
                    Icon(painter = painterResource(id = backButtonResource), contentDescription = "back button")
                }
            }

            if (title != "") {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            top = 2.dp,
                            end = 10.dp,
                            bottom = 2.dp,
                            start = 10.dp
                        ),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = if (state.backButtonResource != null) TextAlign.Center else TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            state.refreshButtonResource?.let { backButtonResource ->
                IconButton(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    onClick = { state.onRefreshClick?.invoke() }
                ) {
                    Icon(painter = painterResource(id = backButtonResource), contentDescription = "back button")
                }
            }
        }
    }
}
