package com.sarria.fake_shanbay_compose.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sarria.fake_shanbay_compose.ui.home.Home
import com.sarria.fake_shanbay_compose.ui.login.Login

//主屏幕
@Composable
fun Main() {
    var login by rememberSaveable {
        mutableStateOf(false)
    }
    Crossfade(targetState = login) {
        if (it) {
            Home()
        } else {
            Login { login = true }
        }
    }
}