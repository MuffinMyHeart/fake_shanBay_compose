package com.sarria.fake_shanbay_compose.ui.main.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
//    Text(modifier = Modifier.background(color = Color.Green), text = "hello world")
    Box(
//        modifier = Modifier.fillMaxSize(),
//        propagateMinConstraints = true,
        contentAlignment = Alignment.Center
    ) {

        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.background(color = Color.Green),
//            text = "min=${minHeight},${minWidth}  max=${maxHeight},${maxWidth}"
            text = "hello"
        )
    }
}