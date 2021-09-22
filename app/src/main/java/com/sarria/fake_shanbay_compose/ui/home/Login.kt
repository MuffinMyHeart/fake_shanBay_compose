package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.utils.rememberVideoViewWithLifecycle

@Composable
fun Login(onLoginEnd: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginContent(onLoginEnd)
    }
}

@Composable
fun LoginContent(onLoginEnd: () -> Unit) {
    VideoBackground()
}

@Composable
fun VideoBackground() {
    val video = rememberVideoViewWithLifecycle()

}


@Composable
fun LoginInput(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember {
            mutableStateOf("")
        }
        var passWord by remember {
            mutableStateOf("")
        }
        Row {
            Text(text = "用户名")
            OutlinedTextField(value = userName, onValueChange = { userName = it })
        }

        Row {
            Text(text = "用户名")
            OutlinedTextField(value = passWord, onValueChange = { passWord = it })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {

}