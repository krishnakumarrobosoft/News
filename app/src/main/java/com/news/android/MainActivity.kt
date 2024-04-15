package com.news.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.news.android.navigation.NavigationHost
import com.news.android.ui.theme.NewsTheme
import com.news.main.GlobalNavigationViewModel
import com.news.models.Source
import com.news.ui.compositions.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Source("","")
        Json.encodeToString(Source("",""))
        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigationRoot()
                }
            }
        }
    }

    @Composable
    private fun NavigationRoot(
    ) {
        val navigationViewModel = hiltViewModel<GlobalNavigationViewModel>()
        NavigationHost(
            navController = rememberNavController(),
            keys = navigationViewModel.keys,
            navigationViewModel = navigationViewModel
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    Button(onClick = { viewModel.getTopHighlights() }) {
        Text("Top Highlights")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsTheme {
        Greeting("Android")
    }
}