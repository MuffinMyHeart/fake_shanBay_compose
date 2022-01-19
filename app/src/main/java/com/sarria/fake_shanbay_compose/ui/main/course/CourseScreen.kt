package com.sarria.fake_shanbay_compose.ui.main.course

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.statusBarsPadding
import com.sarria.fake_shanbay_compose.ui.main.bookTable.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun CourseScreen(modifier: Modifier) {
    ProfilePage()
}

@Composable
fun ProfilePage() {
    var topBarState by remember {
        mutableStateOf(TopBarState.EXPANDED)
    }
    val maxOffsetPx = with(LocalDensity.current) {
        TOP_BAR_MAX_HEIGHT.dp.roundToPx().toFloat() - TOP_BAR_MIN_HEIGHT.dp.roundToPx().toFloat()
    }
    var offsetRatio by remember {
        mutableStateOf(0f)
    }
    var offsetPx by remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberScrollState()


    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = (offsetPx + available.y).coerceIn(-maxOffsetPx, 0f)
                println(available.y)
                if (scrollState.value.toFloat() > maxOffsetPx) {
                    topBarState = TopBarState.COLLAPSED
                    offsetPx = -maxOffsetPx
                    offsetRatio = 1f
                } else {
                    topBarState = TopBarState.EXPANDED
                    offsetPx = delta
                    offsetRatio = (delta / maxOffsetPx).absoluteValue
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {

        val halfAvatarSizePx = with(LocalDensity.current) {
            36.dp.roundToPx()
        }

        val imagePainter = rememberImagePainter(
            data = "https://cataas.com/cat"
        )

        val gradientColor = listOf(Color(0xFF336ADD), Color(0xFF4691EF))

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            val (image, avatar, content) = createRefs()

            Spacer(
                modifier = Modifier
                    .background(brush = Brush.linearGradient(gradientColor))
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .height(TOP_BAR_MAX_HEIGHT.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    },
            )

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            (-halfAvatarSizePx * offsetRatio / 2).roundToInt(),
                            (halfAvatarSizePx * offsetRatio).roundToInt()
                        )
                    }
                    .scale(1f - .2f * offsetRatio)
                    .border(width = 3f.dp, color = Color.White, CircleShape)
                    .size(84.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .constrainAs(avatar) {
                        start.linkTo(parent.start, 32.dp)
                        top.linkTo(image.bottom)
                        bottom.linkTo(image.bottom)
                    }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = imagePainter,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
            }

            Content(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(content) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
            )

        }

        if (topBarState == TopBarState.COLLAPSED) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -maxOffsetPx.roundToInt()
                        )
                    }
                    .shadow(8.dp)
                    .background(brush = Brush.linearGradient(gradientColor))
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .height(TOP_BAR_MAX_HEIGHT.dp),
            )

        }
    }
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        repeat(100) {
            Text(text = "hello")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}