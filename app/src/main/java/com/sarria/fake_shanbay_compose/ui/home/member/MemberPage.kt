package com.sarria.fake_shanbay_compose.ui.home.member

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sarria.fake_shanbay_compose.data.commonState.RefreshState
import com.sarria.fake_shanbay_compose.ui.commonLayout.ShanBaySwipeRefreshIndicator

/**
 * 会员页
 */
@Composable
fun MemberPage(modifier: Modifier) {
    val memberViewModel: MemberViewModel = hiltViewModel()
    val memberState by memberViewModel.state.collectAsState()
    val refreshState = memberState.refreshState

    val swipeState = rememberSwipeRefreshState(isRefreshing = refreshState is RefreshState.Loading)
    SwipeRefresh(
        state = swipeState,
        onRefresh = { memberViewModel.refresh() },
        indicator = { state, trigger ->
            ShanBaySwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                refreshingOffset = 32.dp
            )
        },
        refreshTriggerDistance = 64.dp
    ) {
        val state = rememberScrollState()

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)) {
            repeat(100){
                Text(text = "Test $it")
            }
        }
    }
}