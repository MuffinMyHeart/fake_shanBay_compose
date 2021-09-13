package com.sarria.fake_shanbay_compose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sarria.fake_shanbay_compose.ui.home.Home

@Composable
fun ShanBayApp() {
    Home()
}