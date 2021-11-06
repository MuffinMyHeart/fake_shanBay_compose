package com.sarria.fake_shanbay_compose.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import com.sarria.fake_shanbay_compose.R

enum class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes
    val iconId: Int
) {
    Home(route = "home", title = "首页", iconId = R.drawable.ic_home),
    BookTable(route = "bookTable", title = "书桌", iconId = R.drawable.ic_booktable),
    Course(route = "course", title = "课程", iconId = R.drawable.ic_course),
    Find(route = "find", title = "发现", iconId = R.drawable.ic_find),
    My(route = "my", title = "我的", iconId = R.drawable.ic_my),
}

fun NavDestination.bottomBarScreen(): BottomBarScreen? = when(route){
    "home" -> BottomBarScreen.Home
    "bookTable" -> BottomBarScreen.BookTable
    "course" -> BottomBarScreen.Course
    "find" -> BottomBarScreen.Find
    "my" -> BottomBarScreen.My
    else -> null
}