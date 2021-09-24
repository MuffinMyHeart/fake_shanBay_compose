package com.sarria.fake_shanbay_compose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Home() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            HomeTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color.LightGray)
            ) {


            }
        }
    }
}

@Composable
fun HomeTopAppBar(modifier: Modifier) {

    //这边要用constrain layout 因为需要将搜索栏固定到中心位置但是目前row没有给我们提供这个选项

    ConstraintLayout(modifier = modifier) {
        val (leftText, centerQuery, rightIcons) = createRefs()
        Text(
            modifier = Modifier.constrainAs(leftText) {
                start.linkTo(parent.start)
                top.linkTo(centerQuery.top)
                bottom.linkTo(centerQuery.bottom)
            },
            text = "工作党"
        )

        Row(
            modifier = Modifier
                .constrainAs(centerQuery) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .size(240.dp, 32.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
            Text(text = "测试")
        }

        Row(
            modifier = Modifier
                .constrainAs(rightIcons) {
                    end.linkTo(parent.end)
                    top.linkTo(centerQuery.top)
                    bottom.linkTo(centerQuery.bottom)
                }) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_quest),
                contentDescription = "quest"
            )
        }

    }

}

