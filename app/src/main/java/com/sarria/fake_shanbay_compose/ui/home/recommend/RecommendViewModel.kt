package com.sarria.fake_shanbay_compose.ui.home.recommend

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val api: ShanBayApi
) : ViewModel() {

    private val _state = mutableStateOf(ArticlesState())
    val state: State<ArticlesState> = _state

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                delay(2000)
                _state.value = state.value.copy(articles = api.getArticles())
                _state.value = state.value.copy(isLoading = false)
            } catch (e: Exception) {
                Log.e("recommendViewModel", "getArticles: $e")
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
}

data class ArticlesState(
    val articles: List<Article>? = null,
    val isLoading: Boolean = false,
)