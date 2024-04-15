package com.news.android.navigation

import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.news.api.NavigationKey
import com.news.main.GlobalNavigationViewModel
import com.news.models.Article
import com.news.models.GlobalNavigationState
import com.news.models.GlobalNavigationState.ArticleDetail
import com.news.models.GlobalNavigationState.GoBack
import com.news.models.GlobalNavigationState.Home
import com.news.models.GlobalNavigationState.None
import com.news.ui.compositions.ArticleDetailScreen
import com.news.ui.compositions.HomeScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
internal fun NavigationHost(
    navController: NavHostController,
    keys: List<NavigationKey>,
    navigationViewModel: GlobalNavigationViewModel
) {
    if (keys.isEmpty()) return
    val startDestination = keys.first().key
    NavigationEffects(navController = navController, viewModel = navigationViewModel)
    NavHost(navController = navController, startDestination = startDestination) {
        keys.forEach { screen ->
            when (screen) {
                is ArticleDetail -> composable(screen.key + "?article={article}", arguments = listOf(navArgument("article") {
                    type = NavType.StringType
                }), deepLinks = listOf(navDeepLink { uriPattern = "$DEEPLINK_URI/{article}" })
                ) {
                    val articleJson = it.arguments?.getString("article")
                    getArticleFromJson(articleJson)?.let { article ->
                        ArticleDetailScreen(article)
                    }
                }

                else -> composable(screen.key) {
                    NavigationRouter(
                        screen = screen, it
                    )
                }
            }
        }
    }

}

@Composable
private fun NavigationEffects(
    navController: NavHostController,
    viewModel: GlobalNavigationViewModel,
) {
    val screenState by viewModel.state.collectAsStateWithLifecycle(initialValue = None())

    when (screenState) {
        is GoBack -> {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set(SHOULD_REFRESH, (screenState as GoBack).shouldRefresh)
            navController.navigateUp()
        }

        is ArticleDetail -> {

            (screenState as? ArticleDetail)?.article?.let { article ->
                val articleJson = Json.encodeToString(article)
                navController.navigate(screenState.key + "?article=$articleJson")
            }
        }

        else -> Unit
    }
}

@Composable
fun NavigationRouter(screen: NavigationKey, navBackStackEntry: NavBackStackEntry) {
    when (screen) {
        is Home -> {
            val shouldRefresh = navBackStackEntry.savedStateHandle.get<Boolean>(SHOULD_REFRESH)
            HomeScreen(shouldRefresh ?: false)
        }
    }

}

private fun getArticleFromJson(json: String?): Article? {
    return runCatching { json?.let { Json.decodeFromString<Article?>(it) } }.getOrNull()
}

private const val SHOULD_REFRESH = "should_refresh"
private const val DEEPLINK_URI = "https://www.news.com"
