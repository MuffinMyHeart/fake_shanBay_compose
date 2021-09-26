package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.sarria.fake_shanbay_compose.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max

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
                ShanBayTabIndicator(pagerState = pagerState, tabPositions = tabPositions)
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

/**
 * 暗示器
 */
@ExperimentalPagerApi
@Composable
fun ShanBayTabIndicator(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
) {

//

}

//@ExperimentalPagerApi
//fun PagerState.fixTargetPage(): Int {
//    return when {
//        // If a scroll isn't in progress, return the current page
//        !isScrollInProgress -> currentPage
//        // If the offset is 0f (or very close), return the current page
//        currentPageOffset.absoluteValue < 0.001f -> currentPage
//        // If we're offset towards the start, guess the previous page
//        currentPageOffset < 0 -> (currentPage - 1).coerceAtLeast(0)
//        // If we're offset towards the end, guess the next page
//        else -> (currentPage + 1).coerceAtMost(pageCount - 1)
//    }
//}