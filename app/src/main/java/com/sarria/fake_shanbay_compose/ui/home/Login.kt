package com.sarria.fake_shanbay_compose.ui.home

import android.net.Uri
import android.net.wifi.WifiManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.insets.systemBarsPadding
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
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            LoginAppBar()
            LoginText()
        }

        Column(verticalArrangement = Arrangement.SpaceBetween) {
            LoginRow()
            OtherLoginRow()
        }
    }
}

@Composable
fun OtherLoginRow() {
    Row(
        modifier = Modifier
            .padding(horizontal = 48.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = Color.Black,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "")
        }

        Surface(
            shape = CircleShape,
            color = Color.Black,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "")
        }

        Surface(
            shape = CircleShape,
            color = Color.Black,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
        }

        Surface(
            shape = CircleShape,
            color = Color.Black,
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
        }

    }
}

@Composable
fun LoginRow() {
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp),
            onClick = { }) {
            Icon(imageVector = Icons.Outlined.Phone, contentDescription = "phone icon")
            Text(text = "手机号登录")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp),
            onClick = { }) {
            Icon(imageVector = Icons.Outlined.List, contentDescription = "wechat")
            Text(text = "微信登录")
        }

    }
}

@Composable
fun LoginAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.mipmap.shanbay_logo),
            contentDescription = "Logo"
        )


        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            color = Color.White.copy(alpha = ContentAlpha.medium),
            text = "随便看看 >",
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun LoginText() {
    Text(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 16.dp)
            .width(280.dp),
        text = "Read what the world reads",
        color = Color.White.copy(0.9f),
        style = MaterialTheme.typography.h2,
    )

    Text(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 16.dp)
            .width(240.dp),
        text = "扇贝阅读\n陪你一起, 读懂世界",
        color = Color.White.copy(0.83f),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun VideoBackground() {
    val packageName = LocalContext.current.applicationContext.packageName
    val uri = Uri.parse("android.resource://$packageName/${R.raw.even}")
    val video = rememberVideoViewWithLifecycle()

    AndroidView(factory = { video }) {
        video.setVideoURI(uri)
        video.setOnCompletionListener {
            it.start()
        }
        video.start()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    Login()
}