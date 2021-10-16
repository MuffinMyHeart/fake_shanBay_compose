package com.sarria.fake_shanbay_compose.ui.home.recommend

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarria.fake_shanbay_compose.data.RecommendRepository
import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: RecommendRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ArticlesState())
    val state: StateFlow<ArticlesState> = _state

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            delay(1000)
            try {
                val articleFlow = repository.getArticleByUserId("sarria")
                val articles = articleFlow.stateIn(this).value

//                _state.value = state.value.copy(articles = api.getArticles())
            } catch (e: Exception) {
                Log.e("recommendViewModel", "getArticles: $e")
            } finally {
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
}

data class ArticlesState(
    val articles: List<Article>? = null,
    val isLoading: Boolean = false,
)