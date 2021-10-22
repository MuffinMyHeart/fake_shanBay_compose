package com.sarria.fake_shanbay_compose.ui.home.recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarria.fake_shanbay_compose.data.RecommendRepository
import com.sarria.fake_shanbay_compose.data.commonState.RefreshState
import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.model.ClockOnCardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 推荐页viewmodel
 */
@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: RecommendRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecommendState())
    val state: StateFlow<RecommendState> = _state

    init {
        refreshPage()
    }

    fun refreshPage() {
        viewModelScope.launch {
            _state.update { it.copy(refreshState = RefreshState.Loading) }
            // 文章flow
            val articles = flow { emit(repository.getArticles("sarria").toList()) }
            // 打卡flow
            val clockOnCardInfo = repository.getClockOnCardInfo("sarria")
            // 今日推送flow
            val todayPushMessage = repository.getTodayPushMessage()

            val startTime = System.currentTimeMillis()
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
            }.onCompletion { cause ->
                //至少停留两秒
                val spend = System.currentTimeMillis() - startTime
                if (spend < 2000) delay(2000 - spend)
                if (cause == null) {
                    _state.update { it.copy(refreshState = RefreshState.Success) }
                }
            }.catch { cause: Throwable ->
                _state.update { it.copy(refreshState = RefreshState.Error(cause.message)) }
            }.collect()
        }

    }
}

data class RecommendState(
    val articles: List<Article>? = null,
    val clockOnCardInfo: ClockOnCardInfo? = null,
    val todayPushMessage: List<String>? = null,
    val refreshState: RefreshState = RefreshState.Empty
)