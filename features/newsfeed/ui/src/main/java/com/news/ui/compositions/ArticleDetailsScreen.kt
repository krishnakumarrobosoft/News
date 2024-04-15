package com.news.ui.compositions

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.news.models.Article
import com.news.presentation.models.ArticleDetailState
import com.news.presentation.models.ToolbarState
import com.news.presentation.state.ArticleDetailAction
import com.news.presentation.viewmodel.ArticleDetailViewModel
import com.news.ui.R
import com.news.ui.images.RemoteImage

@Composable
fun ArticleDetailScreen(article: Article, viewModel: ArticleDetailViewModel = hiltViewModel()) {
    val articleDetailState by viewModel.articleDetailState.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.setup(article)
    })
    BackHandler(enabled = true) {
        viewModel.onBack()
    }
    ArticleDetailRouter(state = articleDetailState)
}

@Composable
fun ArticleDetailRouter(state: ArticleDetailState) {
    when (state) {
        is ArticleDetailState.Content -> {
            ArticleDetailToolbarRouter(state)
            if (state.displayErrorMessageType != ArticleDetailAction.DisplayErrorMessageType.None) {
                Toast.makeText(LocalContext.current, displayErrorMessageRouter(state.displayErrorMessageType), Toast.LENGTH_SHORT)
                    .show()
            }
            Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 70.dp)) {
                RemoteImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    imageUrl = state.article.urlToImage.orEmpty(),
                    placeholderRes = R.drawable.ic_launcher_foreground,
                    errorRes = R.drawable.ic_launcher_background,
                    contentDescription = "ArticleImage",
                    optimizeImage = { url, _ -> url },
                    contentScale = ContentScale.FillWidth
                )

                Row {
                    FavoriteCta(modifier = Modifier.padding(top = 10.dp), state.article, onClick = state.onFavoriteClick)
                    Text(
                        text = state.article.author.orEmpty(),
                        modifier = Modifier.padding(start = 2.dp, top = 12.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = state.article.description.orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        else -> Unit
    }

}

@Composable
private fun displayErrorMessageRouter(displayErrorMessageType: ArticleDetailAction.DisplayErrorMessageType): String =
    when (displayErrorMessageType) {
        is ArticleDetailAction.DisplayErrorMessageType.Favorites ->
            stringResource(R.string.favorite_error_message)

        else ->
            ""
    }

@Composable
private fun ArticleDetailToolbarRouter(state: ArticleDetailState.Content) {
    (state.toolbarState as? ToolbarState.Content)?.run {
        StandardToolbar(
            state = this, title = title, modifier = Modifier
                .height(50.dp)
                .border(1.dp, Color.LightGray.copy(alpha = 0.7f))
                .background(Color.LightGray.copy(alpha = 0.1f))
        )
    }
}
