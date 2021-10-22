package com.sarria.fake_shanbay_compose.data.commonState

sealed class RefreshState {
    object Empty : RefreshState()
    object Loading : RefreshState()
    data class Error(val errorMsg: String?) : RefreshState()
    object Success : RefreshState()
}