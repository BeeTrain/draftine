package dev.draftine.advices.presentation.adapter

import android.view.Gravity
import dev.draftine.advices.R
import dev.draftine.advices.presentation.model.AdviceModel
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem
import dev.draftine.ui.text.TextType
import dev.draftine.ui.text.TextView

class AdviceRecyclerItem : RecyclerItem<TextView, AdviceModel>() {

    override val layoutId = R.layout.advice_item

    override fun isRelativeItem(item: Item) = item is AdviceModel

    override fun bind(view: TextView, item: AdviceModel) {
        view.apply {
            gravity = Gravity.CENTER
            applyTextType(TextType.HEADLINE_6)
            text = item.text
        }
    }
}