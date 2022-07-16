package dev.draftine.feed.presentation.model

import android.graphics.drawable.Drawable
import dev.draftine.feed.domain.model.FeedFeature
import dev.draftine.ui.recycler.Item

data class FeedListItem(
    val name: String,
    val startIcon: Drawable,
    override val payload: FeedFeature
) : Item()