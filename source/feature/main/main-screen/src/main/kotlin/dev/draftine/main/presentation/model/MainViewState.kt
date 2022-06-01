package dev.draftine.main.presentation.model

import dev.draftine.ui.recycler.Item

sealed interface MainViewState {

    data class Data(val list: List<Item>) : MainViewState

    object Loading : MainViewState
}