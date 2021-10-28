package com.sarria.fake_shanbay_compose.ui.main.home.member

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sarria.fake_shanbay_compose.data.commonState.RefreshState
import com.sarria.fake_shanbay_compose.ui.commonLayout.ShanBaySwipeRefreshIndicator
import com.sarria.fake_shanbay_compose.ui.commonLayout.noRippleClickable

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
        ) {
            FuncTest(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun FuncTest(modifier: Modifier = Modifier) {
    var num by remember {
        mutableStateOf(0)
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        Icon(
            modifier = Modifier.noRippleClickable {
                num++
            },
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "up"
        )

        Text(text = "$num")

        Icon(
            modifier = Modifier.noRippleClickable(
                enabled = num > 0,
            ) {
                if (num > 0) num--
            },
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "minus"
        )

    }
}