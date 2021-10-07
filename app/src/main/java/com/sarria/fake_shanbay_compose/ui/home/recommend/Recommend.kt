package com.sarria.fake_shanbay_compose.ui.home.recommend

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.model.Article
import com.sarria.fake_shanbay_compose.model.getArticle
import com.sarria.fake_shanbay_compose.ui.commonLayout.VerticalScrollText
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme

@ExperimentalAnimationApi
@Composable
fun RecommendPage(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
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

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                ArticleCardItem(article = getArticle())
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}

@Composable
fun ArticleCardItem(modifier: Modifier = Modifier, article: Article) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .heightIn(max = 170.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = article.imageSrc.toInt()),
                    contentDescription = "image",
                    contentScale = ContentScale.FillWidth
                )

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(
                            color = Color.Black.copy(alpha = .3f)
                        )
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalContentColor provides Color.White) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(id = R.drawable.ic_read),
                            contentDescription = "reds"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${article.totalReads}万",
                            fontSize = 9.sp
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.primary) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_book),
                            contentDescription = "book"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = article.type,fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.englishTitle,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(1000),
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.chineseIntroduction,
                    fontSize = 12.sp,
                    color = LocalContentColor.current.copy(alpha = .6f)
                )
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
            .background(MaterialTheme.colors.background)
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


                Text(
                    text = "66737人参与挑战",
                    fontSize = 9.sp,
                    color = LocalContentColor.current.copy(alpha = .4f)
                )
            }
        }
        Button(
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
            onClick = { },
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "打卡")
        }

    }
}

@ExperimentalAnimationApi
@Composable
fun TodayRow(modifier: Modifier = Modifier) {
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
                    fontSize = 12.sp,
                    color = LocalContentColor.current.copy(.3f)
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

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    Fake_shanBay_composeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                ArticleCardItem(modifier = Modifier.padding(12.dp), article = getArticle())
            }
        }
    }
}

