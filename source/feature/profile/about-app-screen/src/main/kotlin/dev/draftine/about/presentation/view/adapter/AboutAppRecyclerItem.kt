package dev.draftine.about.presentation.view.adapter

import android.view.View
import dev.draftine.about.R
import dev.draftine.about.presentation.model.AppInfoModel
import dev.draftine.ui.list.DataListItem
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class AboutAppRecyclerItem : RecyclerItem<View, AppInfoModel>() {

    override val layoutId = R.layout.about_app_list_item

    override fun isRelativeItem(item: Item) = item is AppInfoModel

    override fun bind(view: View, item: AppInfoModel) {
        (view as DataListItem).apply {
            title = item.title
            caption = item.value
        }
    }

}