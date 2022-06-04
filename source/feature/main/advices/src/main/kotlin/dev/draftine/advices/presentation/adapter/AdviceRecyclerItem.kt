package dev.draftine.advices.presentation.adapter

import android.view.View
import dev.draftine.advices.R
import dev.draftine.advices.presentation.model.AdviceModel
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem
import dev.draftine.ui.text.TextView

class AdviceRecyclerItem : RecyclerItem<View, AdviceModel>() {

    override val layoutId = R.layout.advice_item

    override fun isRelativeItem(item: Item) = item is AdviceModel

    override fun bind(view: View, item: AdviceModel) {
        view.findViewById<TextView>(R.id.advice_item_text).text = item.text
    }
}