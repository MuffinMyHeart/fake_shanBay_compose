package com.sarria.fake_shanbay_compose.ui.home.recommend

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarria.fake_shanbay_compose.data.RecommendRepository
import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.model.ClockOnCardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: RecommendRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecommendState())
    val state: StateFlow<RecommendState> = _state

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val spendTime = measureTimeMillis {
                val articles = flow {
                    emit(repository.getArticles("sarria").toList())
                }

                val clockOnCardInfo = repository.getClockOnCardInfo("sarria")

                val todayPushMessage = repository.getTodayPushMessage()

                //合并流
                combine(
                    articles,
                    clockOnCardInfo,
                    todayPushMessage
                ) { _articles, _clockOnCardInfo, _todayPushMessage ->
                    assert(_articles.size >= 5) { "articles.size < 5" }
                    _state.update {
                        it.copy(
                            articles = _articles,
                            clockOnCardInfo = _clockOnCardInfo,
                            todayPushMessage = _todayPushMessage,
                        )
                    }
                }.catch { cause: Throwable ->
                    Log.e("RecommendViewModel", " ${cause.message}")
                    _state.update { it.copy(onError = true, errorMsg = cause.message) }
                }.collect()
            }

            //如果小于1.5s至少等待1.5s
            if (spendTime < 1500) {
                delay(1500 - spendTime)
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

data class RecommendState(
    val articles: List<Article>? = null,
    val clockOnCardInfo: ClockOnCardInfo? = null,
    val todayPushMessage: List<String>? = null,
    val isLoading: Boolean = false,
    val onError: Boolean = false,
    val errorMsg: String? = null
)