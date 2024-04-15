package com.news.ui.compositions

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.news.models.Article
import com.news.presentation.models.DisplayErrorMessageType
import com.news.presentation.models.HomeScreenState
import com.news.presentation.models.Section
import com.news.presentation.models.ToolbarState
import com.news.presentation.viewmodel.NewsFeedViewModel
import com.news.ui.CircularProgressBar
import com.news.ui.R
import com.news.ui.images.RemoteImage

@Composable
fun HomeScreen(shouldRefresh: Boolean = false, newsFeedViewModel: NewsFeedViewModel = hiltViewModel()) {
    val homeScreenState by newsFeedViewModel.homeScreenState.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        if (shouldRefresh) newsFeedViewModel.loadData()
    })
    ToolbarRouter(state = homeScreenState)
    when (homeScreenState) {
        is HomeScreenState.Content -> {
            ErrorRouter((homeScreenState as? HomeScreenState.Content)?.errorType)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 70.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                (homeScreenState as? HomeScreenState.Content)?.sections?.let {
                    items(it) { section ->
                        SectionRouter(section)
                    }
                }
            }
        }

        HomeScreenState.None -> Text(
            text = stringResource(id = R.string.content_unavailable),
            style = MaterialTheme.typography.titleSmall
        )
    }

}

@Composable
private fun ErrorRouter(errorType: DisplayErrorMessageType?) {
    when (errorType) {
        is DisplayErrorMessageType.Favorite -> DisplayErrorToast(errorMessage = errorType.errorMessage)
        is DisplayErrorMessageType.Generic -> DisplayErrorToast(errorMessage = errorType.errorMessage)
        DisplayErrorMessageType.None -> Unit
        else -> Unit
    }
}

@Composable
private fun DisplayErrorToast(errorMessage: String) {
    Toast.makeText(
        LocalContext.current,
        errorMessage,
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun ToolbarRouter(state: HomeScreenState) {
    when (state) {
        is HomeScreenState.Content -> {
            (state.toolbarState as? ToolbarState.Content)?.run {
                StandardToolbar(
                    state = this, title = title, modifier = Modifier
                        .height(50.dp)
                        .border(1.dp, Color.LightGray.copy(alpha = 0.7f))
                        .background(Color.LightGray.copy(alpha = 0.1f))
                )
            }
        }

        else -> Unit
    }

}


@Composable
fun SectionRouter(section: Section) {
    RowContainerWithTitle(title = section.title) {
        if (section.isLoading) {
            CircularProgressBar()
        }
        Section(
            rowList = section.items,
            onItemClick = section.onArticleClick,
            onFavoriteClick = section.onFavorite
        )
    }
}

@Composable
fun Section(rowList: List<List<Article>>, onItemClick: (Article) -> Unit, onFavoriteClick: (Article) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (item in rowList) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                ArticleRow(item, onItemClick, onFavoriteClick)
            }
        }
    }
}

@Composable
fun RowContainerWithTitle(modifier: Modifier = Modifier, title: String, content: @Composable BoxScope.() -> Unit) {
    Column {
        Text(title)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RectangleShape
            )
            .padding(3.dp),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
private fun ArticleRow(articles: List<Article>, onClick: (Article) -> Unit, onFavoriteClick: (Article) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 5.dp
        )
    ) {
        items(items = articles) { article ->
            ArticleCard(article, onClick = onClick, onFavoriteClick = onFavoriteClick)
        }
    }
}

@Composable
fun ArticleCard(article: Article, onClick: (Article) -> Unit, onFavoriteClick: (Article) -> Unit) {
    Column(
        modifier = Modifier
            .border(1.dp, Color.LightGray)
            .clickable(onClick = {
                onClick(article)
            })
    ) {
        RemoteImage(
            modifier = Modifier
                .width(120.dp)
                .height(70.dp),
            imageUrl = article.urlToImage.orEmpty(),
            placeholderRes = R.drawable.ic_launcher_foreground,
            errorRes = R.drawable.ic_launcher_background,
            contentDescription = "ArticleImage",
            optimizeImage = { url, _ -> url },
            contentScale = ContentScale.FillBounds
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(end = 30.dp)) {
                Text(
                    modifier = Modifier
                        .widthIn(max = 60.dp)
                        .padding(start = 2.dp),
                    text = article.title.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .widthIn(max = 60.dp)
                        .padding(start = 2.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = article.author.orEmpty(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            FavoriteCta(article = article, onClick = onFavoriteClick, modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
fun FavoriteCta(modifier: Modifier = Modifier, article: Article, onClick: (Article) -> Unit) {
    val icon = if (article.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Box(
        modifier = modifier
            .size(30.dp)
            .clickable(onClick = {
                onClick(article)
            }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Favorite Icon",
            tint = if (article.isFavorite) Color.Red else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}
