package dev.draftine.app.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

private const val DEFAULT_TOP_INSET_DP = 24f
private const val DEFAULT_BOTTOM_INSET_DP = 48f

class ApplicationViewModel : BaseViewModel() {

    val topInsetStateFlow = MutableStateFlow(DEFAULT_TOP_INSET_DP)

    val bottomInsetStateFlow = MutableStateFlow(DEFAULT_BOTTOM_INSET_DP)

    fun updateTopInset(topInsetDp: Float) {
        topInsetStateFlow.value = topInsetDp
    }

    fun updateBottomInset(bottomInsetDp: Float) {
        bottomInsetStateFlow.value = bottomInsetDp
    }
}