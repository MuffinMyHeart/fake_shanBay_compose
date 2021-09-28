package com.sarria.fake_shanbay_compose.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import com.google.android.material.animation.ArgbEvaluatorCompat
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.commonLayout.ScrollableTabRow
import com.sarria.fake_shanbay_compose.ui.commonLayout.TabPosition
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor
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
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
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
            listOf("推荐", "畅读会员", "赠礼盒", "短文", "经典", "小说", "人物", "科普", "生活")
        }
        val primaryColor = MaterialTheme.colors.primary
        val colors = remember {
            listOf(
                primaryColor,
                Color.Blue,
                Color.Red,
                Color.Cyan,
                Color.Gray,
                Color.Magenta,
                Color.Black,
                Color.Green,
                primaryColor
            )
        }
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = pages.size)


        ScrollableTabRow(
            backgroundColor = MaterialTheme.colors.surface,
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 4.dp,
            indicator = { tabPositions ->
                ShanBayTabIndicator(
                    pagerState = pagerState,
                    tabPositions = tabPositions,
                    height = 4.dp,
                    width = 12.dp,
                    colors = colors
                )
            },
            divider = { Spacer(modifier = Modifier.height(8.dp)) }
        ) {

            pages.forEachIndexed { index, title ->
                Tab(
                    enabled = false,
                    modifier = Modifier.padding(4.dp),
                    selected = pagerState.currentPage == index,
                    onClick = {},
                ) {
                    Text(modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(vertical = 8.dp), text = title)
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            if (page == 0) {
                TestPage()
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Card(Modifier.padding(16.dp), elevation = 1.dp) {
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
}

class UserState {
    var userName by mutableStateOf("")
    var password by mutableStateOf("")
}

data class User(
    var userName: String = "",
    var password: String = ""
)

@Composable
fun TestPage() {
    val users = remember {
        listOf(mutableStateOf(User()), mutableStateOf(User()))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        users.forEachIndexed { index, user ->
            Row {
                TextField(
                    modifier = Modifier.width(200.dp),
                    value = user.value.userName,
                    onValueChange = { user.value = user.value.copy(userName = it) },
                    label = {
                        Text(
                            text = "用户$index 用户名"
                        )
                    })
                Text(text = "用户$index 的用户名为 ${user.value.userName}")

            }
            Row {
                TextField(
                    modifier = Modifier.width(200.dp),
                    value = user.value.password,
                    onValueChange = { user.value = user.value.copy(password = it) },
                    label = {
                        Text(
                            text = "用户$index 密码"
                        )
                    })
                Text(text = "用户$index 的密码为 ${user.value.password}")
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
                centerVerticallyTo(centerQuery)

            },
            text = "工作党"
        )

        Row(
            modifier = Modifier
                .constrainAs(centerQuery) {
                    start.linkTo(leftText.end, margin = 8.dp)
                    end.linkTo(rightIcons.start, margin = 8.dp)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
                .size(240.dp, 32.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colors.background.copy(alpha = .5f))
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
                    centerVerticallyTo(centerQuery)
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

@ExperimentalPagerApi
@Composable
fun ShanBayTabIndicator(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
    colors: List<Color>,
    width: Dp = 24.dp,
    height: Dp = 2.dp,
) {
    Box(
        Modifier
            .composed {

                if (pagerState.pageCount == 0) return@composed this

                val targetPage = pagerState.fixedTargetPage()
                val currentColor = colors[pagerState.currentPage]
                val targetColor = colors[targetPage]
                val currentTab = tabPositions[pagerState.currentPage]
                val targetTab = tabPositions.getOrNull(targetPage)

                val targetIndicatorOffset: Dp
                val color: Color
                if (targetTab != null) {
                    val targetDistance = (targetPage - pagerState.currentPage).absoluteValue
                    //滑动完成百分数
                    val fraction =
                        (pagerState.currentPageOffset / max(targetDistance, 1)).absoluteValue

                    targetIndicatorOffset = lerp(
                        currentTab.left + (currentTab.width - width) / 2,
                        targetTab.left + (targetTab.width - width) / 2,
                        fraction
                    )

                    println("targetDistance： $targetDistance , fraction: $fraction , currentPage:${pagerState.currentPage},currentOffSet: ${pagerState.currentPageOffset}  , targetPage: $targetPage")

                    color = lerp(currentColor, targetColor, fraction)


                } else {
                    targetIndicatorOffset = currentTab.left + (currentTab.width - width) / 2
                    color = currentColor
                }

                fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart)
                    .offset(targetIndicatorOffset)
                    .width(width)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(height))
                    .height(height)
                    .background(color = color)
            }
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(height))
//            .height(height)
//            .background(color = color)
    )
}


@ExperimentalPagerApi
fun PagerState.fixedTargetPage() = when {
    !isScrollInProgress -> currentPage
    currentPageOffset.absoluteValue < 0.001f -> currentPage
    currentPageOffset < 0 -> floor(currentPageOffset + currentPage).toInt().coerceAtLeast(0)
    else -> ceil(currentPageOffset + currentPage).toInt()
        .coerceAtMost((pageCount - 1).coerceAtLeast(0))

}