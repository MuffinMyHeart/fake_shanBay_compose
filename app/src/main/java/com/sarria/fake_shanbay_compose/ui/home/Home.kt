package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Home() {
    var needToSplash by rememberSaveable {
        mutableStateOf(true)
    }

    if (needToSplash) {
        SplashScreen(onNeedToSplash = { needToSplash = !needToSplash })
    } else {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        }
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(it),
            text = "hello"
        )
    }
}

//闪屏页
@Composable
fun SplashScreen(onNeedToSplash: () -> Unit = {}) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.compose_logo),
                contentDescription = null
            )

            Text(text = "这是一个compose 测试")
        }
    }


    //3秒后进入home
//    LaunchedEffect(key1 = true) {
//        launch {
//            delay(3000)
//            onNeedToSplash()
//        }
//    }
}

@Composable
fun HomeTopAppBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.statusBarsPadding()
        ) {
            Text(text = "这是一个简单的appbar")
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Fake_shanBay_composeTheme {
        SplashScreen()
    }
}
