package dev.draftine.main.presentation.model

import dev.draftine.ui.recycler.Item

sealed interface MainScreenState {

    data class Content(val list: List<Item>) : MainScreenState

    object Loading : MainScreenState
}