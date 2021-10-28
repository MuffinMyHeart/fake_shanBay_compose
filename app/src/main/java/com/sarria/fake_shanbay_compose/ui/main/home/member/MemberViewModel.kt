package com.sarria.fake_shanbay_compose.ui.main.home.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarria.fake_shanbay_compose.data.commonState.RefreshState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 会员viewmodel
 */
@HiltViewModel
class MemberViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(MemberState())
    val state: StateFlow<MemberState> = _state

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(refreshState = RefreshState.Loading) }
            delay(2000)
            _state.update { it.copy(refreshState = RefreshState.Success) }
        }
    }
}


data class MemberState(
    val refreshState: RefreshState = RefreshState.Empty
)