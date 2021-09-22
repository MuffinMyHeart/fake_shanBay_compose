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

@Composable
fun HomeTopAppBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        Text(text = "简单")
    }
}

