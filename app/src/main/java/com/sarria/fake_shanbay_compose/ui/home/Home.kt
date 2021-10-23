package com.sarria.fake_shanbay_compose.ui.home


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.commonLayout.ScrollableTabRow
import com.sarria.fake_shanbay_compose.ui.commonLayout.TabPosition
import com.sarria.fake_shanbay_compose.ui.commonLayout.VerticalScrollText
import com.sarria.fake_shanbay_compose.ui.theme.DarkRed
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import com.sarria.fake_shanbay_compose.ui.theme.LowAlpha
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun Home() {
    BackgroundSurface(modifier = Modifier.fillMaxSize()) {
        Column {
            HomeTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            )

            ScrollPage(
                Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
            )
        }
    }
}

@Composable
fun ScrollPage(modifier: Modifier) {
    Column(modifier = modifier) {
        val pages = remember {
            listOf("推荐", "畅读会员", "短文", "经典", "小说", "人物", "科普", "生活")
        }
        val primaryColor = MaterialTheme.colors.primary
        val colors = remember {
            listOf(
                primaryColor,
                Color(0xFFD8A878),
                primaryColor,
                primaryColor,
                primaryColor,
                primaryColor,
                primaryColor,
                primaryColor
            )
        }
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        val currentPage = pagerState.currentPage

        ScrollableTabRow(
            backgroundColor = MaterialTheme.colors.background,
            selectedTabIndex = currentPage,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                ShanBayTabIndicator(
                    pagerState = pagerState,
                    tabPositions = tabPositions,
                    height = 4.dp,
                    width = 16.dp,
                    colors = colors
                )
            },
            divider = { Spacer(modifier = Modifier.height(2.dp)) }
        ) {

            pages.forEachIndexed { index, title ->
                Tab(
                    enabled = false,
                    modifier = Modifier.padding(4.dp),
                    selected = currentPage == index,
                    onClick = {},
                    selectedContentColor = LocalContentColor.current.copy(.73f),
                    unselectedContentColor = LocalContentColor.current.copy(ContentAlpha.disabled)
                ) {

                    val scale by animateFloatAsState(targetValue = if (currentPage == index) 1.1f else 1f)
                    if (title == "畅读会员") {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
//                                        pagerState.scrollToPage(index)
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                                .scale(scale)
                                .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 4.dp),
                            text = title,
                            fontFamily = FontFamily.Monospace,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight(1000),
                            color = Color(0xFFD8A878),
                            style = LocalTextStyle.current.copy(fontWeight = FontWeight(530))
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
//                                        pagerState.scrollToPage(index)
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                                .scale(scale)
                                .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 4.dp),
                            text = title,
                            style = LocalTextStyle.current.copy(fontWeight = FontWeight(530))
                        )
                    }
                }
            }
        }



        HorizontalPager(
            pages.size,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
        ) { page ->
//            RecommendPage(
//                modifier = Modifier
//                    .fillMaxSize()
//            )
//
//            MemberPage(
//                modifier = Modifier
//                    .fillMaxSize()
//            )


            Card {
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


@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = "工作党",
                fontWeight = FontWeight(530),
                color = LocalContentColor.current.copy(alpha = .5f),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .alpha(.5f)
                    .size(8.dp)
                    .align(Alignment.Bottom),
                painter = painterResource(id = R.drawable.ic_expand_triangle),
                contentDescription = "quest"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier
                .weight(1f)
                .size(240.dp, 32.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colors.surface.copy(LowAlpha))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alpha(.4f)
                    .size(16.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
            VerticalScrollText(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                scrollList = listOf("考研", "大英百科", "听说读写全面提升"),
                textStyle = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight(500),
                    color = LocalContentColor.current.copy(.4f)
                )
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .alpha(.6f)
                    .size(22.dp),
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = "quest"
            )
            Spacer(modifier = Modifier.width(8.dp))
            RingingBell()
        }

    }

}

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
                val currentPage = pagerState.currentPage
                val currentColor = colors[currentPage]
                val targetColor = colors[targetPage]
                val currentTab = tabPositions[currentPage]
                val targetTab = tabPositions.getOrNull(targetPage)
                val currentOffset = pagerState.currentPageOffset

                val targetIndicatorOffset: Dp
                val color: Color
                if (targetTab != null && targetPage != currentPage) {
                    val targetDistance = (targetPage - currentPage).absoluteValue
                    //滑动完成百分数
                    val fraction = (currentOffset.absoluteValue / targetDistance)

                    targetIndicatorOffset = lerp(
                        currentTab.left + (currentTab.width - width) / 2,
                        targetTab.left + (targetTab.width - width) / 2,
                        fraction
                    )

                    println("targetDistance： $targetDistance , fraction: $fraction , currentPage:${currentPage},currentOffSet: $currentOffset  , targetPage: $targetPage")
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
    )
}

@Composable
fun RingingBell(
    messages: List<String> = listOf("hello", "world", "I", "love", "you")
) {

    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 800
                30f at 200
                -30f at 600
                0f at 800
            },
            repeatMode = RepeatMode.Restart
        )
    )
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2400
                1f at 0
                1.5f at 1200
            },
            repeatMode = RepeatMode.Restart
        )
    )

    Box {
        Icon(
            modifier = Modifier
                .rotate(rotation)
                .alpha(.6f)
                .size(24.dp),
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "quest"
        )

        Box(
            modifier = Modifier
                .offset(10.dp, (-6).dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .scale(scale)
                    .size(16.dp, 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(DarkRed),
            )

            Text(
                color = Color.White,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                text = "16"
            )
        }

    }
}


fun PagerState.fixedTargetPage() = when {
    !isScrollInProgress -> currentPage
    currentPageOffset.absoluteValue < 0.001f -> currentPage
    currentPageOffset < 0 -> floor(currentPageOffset + currentPage).toInt().coerceAtLeast(0)
    else -> ceil(currentPageOffset + currentPage).toInt()
        .coerceAtMost((pageCount - 1).coerceAtLeast(0))
}

@Preview(showBackground = true)
@Composable
fun HomeAppBarPreView() {
    Fake_shanBay_composeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                HomeTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                )
            }
        }
    }
}