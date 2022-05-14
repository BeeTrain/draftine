package dev.draftine.settings.presentation.view.adapter

import android.view.View
import dev.draftine.settings.R
import dev.draftine.settings.presentation.model.SettingsListItem
import dev.draftine.ui.list.DataListItem
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class SettingsListRecyclerItem(
    private val onSettingItemClick: ((SettingsListItem) -> Unit)?
) : RecyclerItem<View, SettingsListItem>() {

    override val layoutId = R.layout.settings_list_item

    override fun isRelativeItem(item: Item) = item is SettingsListItem

    override fun bind(view: View, item: SettingsListItem) {
        (view as DataListItem).apply {
            label = item.name
            endIcon = item.endIcon
            onClickListener = { onSettingItemClick?.invoke(item) }
        }
    }
}