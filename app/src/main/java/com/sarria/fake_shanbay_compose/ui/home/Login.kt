package com.sarria.fake_shanbay_compose.ui.home

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
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
    val packageName = LocalContext.current.applicationContext.packageName
    val uri = Uri.parse("android.resource://$packageName/${R.raw.even}" )
    val video = rememberVideoViewWithLifecycle(uri)

    AndroidView(factory = {video})
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