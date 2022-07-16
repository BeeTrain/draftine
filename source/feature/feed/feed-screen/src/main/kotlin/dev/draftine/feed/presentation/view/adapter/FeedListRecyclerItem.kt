package dev.draftine.feed.presentation.view.adapter

import android.view.View
import dev.draftine.feed.R
import dev.draftine.feed.presentation.model.FeedListItem
import dev.draftine.ui.list.DataListItem
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class FeedListRecyclerItem(
    private val onFeedItemClick: ((FeedListItem) -> Unit)?
) : RecyclerItem<View, FeedListItem>() {

    override val layoutId = R.layout.feed_list_item

    override fun isRelativeItem(item: Item) = item is FeedListItem

    override fun bind(view: View, item: FeedListItem) {
        (view as DataListItem).apply {
            label = item.name
            startIcon = item.startIcon
            onClickListener = { onFeedItemClick?.invoke(item) }
        }
    }
}