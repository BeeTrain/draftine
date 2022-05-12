package dev.draftine.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewHolder<out V : View, I : Item>(
    val view: V
) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(item: I)

    open fun onBind(item: I, payloads: List<Any>) {
        onBind(item)
    }

    open fun onViewDetached() = Unit
}