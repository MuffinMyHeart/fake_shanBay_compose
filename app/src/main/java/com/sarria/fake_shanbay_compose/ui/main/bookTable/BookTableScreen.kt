package com.sarria.fake_shanbay_compose.ui.main.bookTable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import com.sarria.fake_shanbay_compose.R

@Composable
fun BookTableScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyTop = true
                ),
                modifier = Modifier.height(64.dp),
                backgroundColor = Color.White.copy(alpha = .6f)
            ) {

            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {

            Column(
                modifier = Modifier
                    .blur(32.dp)
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {
                repeat(200) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .onGloballyPositioned { layoutCoordinates ->
//                                val offset = layoutCoordinates.size.height
//                                val blurOffset =  if (offset < 0) 0 else
//                            }
                            ,
                        elevation = 0.dp
                    ) {
                        Image(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(id = R.mipmap.compose_logo),
                            contentDescription = "compose logo",
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .height(96.dp)
                .blur(96.dp)
        )
    }
}
