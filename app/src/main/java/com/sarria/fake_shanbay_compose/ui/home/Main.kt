package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable

//主屏幕
@Composable
fun Main() {
    var login by rememberSaveable {
        mutableStateOf(false)
    }
    if (login) {
        Home()
    } else {
        Login { login = true }
    }
}