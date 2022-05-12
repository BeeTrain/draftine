package dev.draftine.settings.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import dev.draftine.settings.R
import dev.draftine.settings.presentation.model.SettingsToggleItem
import dev.draftine.ui.list.ToggleListItem
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class SettingsToggleRecyclerItem(
    private val onToggleSettingClick: ((SettingsToggleItem, Boolean) -> Unit)?
) : RecyclerItem<View, SettingsToggleItem>() {

    override val layoutId = R.layout.settings_toggle_list_item

    override fun isRelativeItem(item: Item) = item is SettingsToggleItem

    override fun bind(view: View, item: SettingsToggleItem) {
        (view as ToggleListItem).apply {
            label = item.name
            isChecked = item.isEnabled
            checkedChangeListener = { _, isChecked ->
                onToggleSettingClick?.invoke(item, isChecked)
            }
        }
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<SettingsToggleItem> {
        return object : DiffUtil.ItemCallback<SettingsToggleItem>() {
            override fun areItemsTheSame(oldItem: SettingsToggleItem, newItem: SettingsToggleItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: SettingsToggleItem, newItem: SettingsToggleItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: SettingsToggleItem, newItem: SettingsToggleItem): Any? {
                if (oldItem.payload != newItem.payload) return newItem.payload
                return super.getChangePayload(oldItem, newItem)
            }
        }
    }
}