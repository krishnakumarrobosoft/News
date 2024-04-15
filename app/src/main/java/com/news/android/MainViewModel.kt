package com.news.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.kotlin.coroutines.api.DispatcherProvider
import com.news.main.usecases.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
                                        private val dipatcherProvider:DispatcherProvider) : ViewModel() {

    fun getTopHighlights(){
        viewModelScope.launch {
            try {
                val result = getTopHeadlinesUseCase.getTopHeadlinesArticles(mapOf("sources" to "techcrunch"))
                Log.d("newstest", "got the result $result")
            } catch (e:Exception){
                Log.d("newstest", "got the exception $e")
            }
        }
    }
}