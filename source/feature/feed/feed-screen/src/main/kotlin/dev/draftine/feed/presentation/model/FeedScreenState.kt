package dev.draftine.feed.presentation.model

import dev.draftine.ui.recycler.Item

sealed interface FeedScreenState {

    data class Content(val list: List<Item>) : FeedScreenState

    object Loading : FeedScreenState
}