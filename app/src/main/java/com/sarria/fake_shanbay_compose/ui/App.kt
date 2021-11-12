package com.sarria.fake_shanbay_compose.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.login.Login
import com.sarria.fake_shanbay_compose.ui.main.MainScreen

//主屏幕
@Composable
fun App() {
    var login by rememberSaveable {
        mutableStateOf(false)
    }

    BackgroundSurface {
        if (login) {
            MainScreen()
        } else {
            Login { login = true }
        }
    }

}