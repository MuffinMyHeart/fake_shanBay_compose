package com.sarria.fake_shanbay_compose.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsPadding
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.commonLayout.noRippleClickable
import com.sarria.fake_shanbay_compose.ui.main.home.Home


/**
 * 主界面
 */
@Composable
fun Main() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Home(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            BottomNavigationBar()
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val backgroundColor = if (isSystemInDarkTheme())  Color(0xFF181818) else MaterialTheme.colors.background
    Surface(elevation = 2.dp,color = backgroundColor) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(top = 8.dp, bottom = 8.dp)
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            var currentNav by remember {
                mutableStateOf(BottomNav.Home)
            }
            BottomNav.values().forEach {
                BottomItem(bottomNav = it, currentNav = currentNav) {
                    currentNav = it
                }
            }
        }
    }
}

@Composable
fun BottomItem(
    bottomNav: BottomNav,
    currentNav: BottomNav,
    onclick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (currentNav == bottomNav) 1.1f else 1f
    )
    val alpha by animateFloatAsState(targetValue = if (currentNav == bottomNav) .7f else .3f)
    Column(
        modifier = Modifier
            .noRippleClickable { onclick() }
            .width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            Icon(
                modifier = Modifier.size(20.dp * scale),
                painter = painterResource(id = bottomNav.drawableId),
                contentDescription = bottomNav.text
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = bottomNav.text, fontSize = 13.sp * scale)
        }
    }
}


enum class BottomNav(
    @DrawableRes
    val drawableId: Int,
    val text: String
) {
    Home(drawableId = R.drawable.ic_home, text = "首页"),
    BookTable(drawableId = R.drawable.ic_booktable, text = "书桌"),
    Course(drawableId = R.drawable.ic_course, text = "课程"),
    Find(drawableId = R.drawable.ic_find, text = "发现"),
    My(drawableId = R.drawable.ic_my, text = "我的"),
}
