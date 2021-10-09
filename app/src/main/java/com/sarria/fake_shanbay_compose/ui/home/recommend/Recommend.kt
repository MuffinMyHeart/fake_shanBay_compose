package com.sarria.fake_shanbay_compose.ui.home.recommend

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.commonLayout.ArticleCard
import com.sarria.fake_shanbay_compose.ui.commonLayout.ArticleCardWithBigImage
import com.sarria.fake_shanbay_compose.ui.commonLayout.VerticalScrollText
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import com.sarria.fake_shanbay_compose.ui.theme.LowAlpha

@ExperimentalAnimationApi
@Composable
fun RecommendPage(
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()
    val recommendViewModel: RecommendViewModel = hiltViewModel()
    val articles = recommendViewModel.state.value.articles
    val isLoading = recommendViewModel.state.value.isLoading
    LazyColumn(
        modifier = modifier,
        state = state,
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                ClockOnCard(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(24.dp))
                TodayRow(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        if (articles.isNullOrEmpty() || isLoading) {
            item {
                CircularProgressIndicator()
            }
        } else {
            itemsIndexed(articles) { index, item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    if (index == 0) {
                        ArticleCardWithBigImage(
                            modifier = Modifier.fillMaxWidth(),
                            article = item
                        )
                    } else {
                        ArticleCard(
                            modifier = Modifier.fillMaxWidth(),
                            article = item
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


//打卡行
@Composable
fun ClockOnCard(modifier: Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface.copy(if (isSystemInDarkTheme()) 1f else LowAlpha))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "152",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = LocalContentColor.current.copy(alpha = .7f)
                )
                Text(
                    text = "打卡(天)",
                    fontSize = 9.sp,
                    color = LocalContentColor.current.copy(alpha = .4f)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = buildAnnotatedString {
                        append("今日已读")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                            )
                        ) {
                            append(" 0 ")
                        }
                        append("篇")
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = LocalContentColor.current.copy(alpha = .7f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(12.dp)
                                .clip(CircleShape)
                                .border(.5.dp, Color.White, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.heads1),
                                contentDescription = "heads"
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(12.dp)
                                .clip(CircleShape)
                                .border(.5.dp, Color.White, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.heads2),
                                contentDescription = "heads"
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .border(.5.dp, Color.White, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.mipmap.heads3),
                                contentDescription = "heads"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "66737人参与挑战 >",
                        fontSize = 9.sp,
                        color = LocalContentColor.current.copy(alpha = .4f)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = 20.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "打卡",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                color = MaterialTheme.colors.surface
            )
        }

    }
}

@ExperimentalAnimationApi
@Composable
fun TodayRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "今日短文",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            VerticalScrollText(
                scrollList = listOf("中国立场，全球视野", "每晚 7:00 准时更新"),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 13.sp,
                    color = LocalContentColor.current.copy(.3f),
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        CompositionLocalProvider(LocalContentAlpha provides .5f) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "全部",
                    fontWeight = FontWeight(530),
                    fontSize = 14.sp,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_right_24),
                    contentDescription = "all"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAppBarPreView() {
    Fake_shanBay_composeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                ClockOnCard(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun TodayRowPreView() {
    Fake_shanBay_composeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                TodayRow(Modifier.fillMaxWidth())
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun RecommendPreview() {
    Fake_shanBay_composeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RecommendPage()
        }
    }
}




