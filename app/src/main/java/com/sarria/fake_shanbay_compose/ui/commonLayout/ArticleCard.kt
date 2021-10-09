package com.sarria.fake_shanbay_compose.ui.commonLayout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.model.Article
import com.sarria.fake_shanbay_compose.model.getArticle
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import com.sarria.fake_shanbay_compose.ui.theme.LowAlpha

@Composable
fun ArticleCardWithBigImage(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface.copy(if (isSystemInDarkTheme()) 1f else LowAlpha),
        contentColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .heightIn(max = 180.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = article.imageUrl.toInt()),
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
                        Text(text = article.type, fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.englishTitle,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = article.chineseIntroduction,
                    fontSize = 12.sp,
                    color = LocalContentColor.current.copy(alpha = .6f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                ArticleCardItemBottom(
                    Modifier.fillMaxWidth(),
                    article.level,
                    article.totalWords,
                    article.difficulty,
                    article.totalComments
                )
            }

        }
    }
}

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface.copy(if (isSystemInDarkTheme()) 1f else LowAlpha),
        contentColor = MaterialTheme.colors.onSurface
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp)
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
                    Text(text = article.type, fontSize = 13.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {

                    Text(
                        text = article.englishTitle,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = article.chineseIntroduction,
                        fontSize = 12.sp,
                        color = LocalContentColor.current.copy(alpha = .6f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Spacer(modifier = Modifier.width(32.dp))

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = article.imageUrl.toInt()),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
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
            }

            ArticleCardItemBottom(
                Modifier.fillMaxWidth(),
                article.level,
                article.totalWords,
                article.difficulty,
                article.totalComments
            )

        }
    }
}

@Composable
fun ArticleCardItemBottom(
    modifier: Modifier,
    level: String,
    totalWords: Int,
    difficulty: String?,
    totalComments: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            1.dp,
                            LocalContentColor.current.copy(alpha = .1f)
                        ), CircleShape
                    )
                    .padding(start = 8.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
                text = level,
                color = LocalContentColor.current.copy(alpha = .6f),
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Icon(
                    modifier = Modifier
                        .padding(1.dp)
                        .size(13.dp)
                        .alpha(.7f),
                    painter = painterResource(id = R.drawable.ic_words),
                    contentDescription = "words"
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = "${totalWords}词",
                    color = LocalContentColor.current.copy(alpha = .7f),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (difficulty != null) {
                Text(
                    text = difficulty,
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface)
                        .padding(horizontal = 2.dp),
                    color = MaterialTheme.colors.primary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(13.dp)
                    .offset(y = 1.dp)
                    .alpha(.7f),
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "comment"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = totalComments.toString(),
                color = LocalContentColor.current.copy(alpha = .7f),
                fontSize = 11.sp
            )
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
                ArticleCardWithBigImage(modifier = Modifier.padding(12.dp), article = getArticle())
                Spacer(modifier = Modifier.height(12.dp))
                ArticleCard(modifier = Modifier.padding(12.dp), article = getArticle())
            }
        }
    }
}