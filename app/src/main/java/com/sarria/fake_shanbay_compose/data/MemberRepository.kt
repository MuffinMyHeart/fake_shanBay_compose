package com.sarria.fake_shanbay_compose.data

import android.app.Application
import coil.ImageLoader
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi

class MemberRepository(
    private val shanBayApi: ShanBayApi,
    private val imageLoader: ImageLoader,
    private val applicationContext: Application
) {

}