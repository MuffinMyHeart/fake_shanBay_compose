package com.sarria.fake_shanbay_compose.data.model

import com.sarria.fake_shanbay_compose.R

data class Article(
    val imageUrl: String,
    val totalReads: Float,
    val type: String,
    val englishTitle: String,
    val chineseIntroduction: String,
    val level: String,
    val totalWords: Int,
    val totalComments: Int,
    val difficulty: String
)

fun getArticle(): Article {
    return Article(
        imageUrl = R.mipmap.image1.toString(),
        totalReads = 256.3f,
        type = "商业",
        englishTitle = "Jetpack Compose The New Generation Of Android UI Kit",
        chineseIntroduction = "安卓最新的开发工具包，jetpack compose 你学会了吗？",
        level = "初级（高考）",
        totalWords = 400,
        totalComments = 300,
        difficulty = "难度适中"
    )
}

val imageUrl = listOf(
    R.mipmap.image1.toString(),
    R.mipmap.image2.toString(),
    R.mipmap.image3.toString(),
    R.mipmap.image4.toString(),
    R.mipmap.image5.toString()
)
val totalReads = listOf(9.3f, 18.2f, 32.1f, 21.5f, 45.2f)
val type = listOf("成长", "商业", "人文", "生活", "文娱")
val englishTitle = listOf(
    "Build Better Apps Faster With Jetpack Compose",
    "Jetpack Compose Is Now 1.0: Announcing Android's Modern Toolkit",
    "Understanding Jetpack Compose — part 1 of 2",
    "To-Do App with Jetpack Compose MVVM",
    "Compose for Desktop UI Framework",
)

val chineseIntroduction = listOf(
    "使用Jetpack Compose更快的构建出色应用。",
    "Jetpack Compose现在已经发布1.0.0版本,这意味着安卓现代UI开发工具包已经到来！",
    "这是一个未完成的APP 使用Jetpack Compose MVVM架构！",
    "理解Jetpack Compose - 第二章 第一部分！",
    "在桌面端应用Jetpack Compose架构！"
)

val level = listOf("初级（高考）", "中级 （四级）", "高级 （六级\\考研）")

val totalWords = listOf(432, 453, 537, 621, 475)

val totalComments = listOf(32, 47, 85, 12, 42)

val difficulty = listOf("难度适中", "较难", "简单")


fun getArticles(): List<Article> {
    val list = mutableListOf<Article>()
    repeat(5) {
        list.add(
            Article(
                imageUrl = imageUrl[it],
                totalReads = totalReads[it],
                type = type[it],
                englishTitle = englishTitle[it],
                chineseIntroduction = chineseIntroduction[it],
                level = level.random(),
                totalWords = totalWords[it],
                totalComments = totalComments[it],
                difficulty = difficulty.random()
            )
        )
    }
    return list
}
