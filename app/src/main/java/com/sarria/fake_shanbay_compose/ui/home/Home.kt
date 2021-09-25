package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sarria.fake_shanbay_compose.R
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun Home() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            HomeTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            )

            ScrollPage(Modifier.fillMaxSize())
        }
    }
}

@ExperimentalPagerApi
@Composable
fun ScrollPage(modifier: Modifier) {
    Column(modifier = modifier) {
        val pages = remember {
            listOf("推荐", "畅du会员", "赠礼盒", "短文", "经典", "小说", "人物", "科普", "生活")
        }
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = pages.size)

        ScrollableTabRow(
            backgroundColor = MaterialTheme.colors.surface,
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = Color.Green
                )
            }
        ) {

            pages.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                Card(Modifier.padding(16.dp)) {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = "Page: ${pages[page]}",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun HomeTopAppBar(modifier: Modifier) {

    //这边要用constrain layout 因为需要将搜索栏固定到中心位置但是目前row没有给我们提供这个选项

    ConstraintLayout(modifier = modifier) {
        val (leftText, centerQuery, rightIcons) = createRefs()
        Text(
            modifier = Modifier.constrainAs(leftText) {
                start.linkTo(parent.start)
                top.linkTo(centerQuery.top)
                bottom.linkTo(centerQuery.bottom)
            },
            text = "工作党"
        )

        Row(
            modifier = Modifier
                .constrainAs(centerQuery) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .size(240.dp, 32.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
            Text(text = "测试")
        }

        Row(
            modifier = Modifier
                .constrainAs(rightIcons) {
                    end.linkTo(parent.end)
                    top.linkTo(centerQuery.top)
                    bottom.linkTo(centerQuery.bottom)
                }) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
        }

    }

}

