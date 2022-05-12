package dev.draftine.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil

abstract class RecyclerItem<V : View, I : Item> {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun isRelativeItem(item: Item): Boolean

    abstract fun bind(view: V, item: I)

    open fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerViewHolder<V, I> {
        val view = layoutInflater.inflate(layoutId, parent, false) as V
        return object : RecyclerViewHolder<V, I>(view) {

            override fun onBind(item: I) {
                bind(view, item)
            }
        }
    }

    open fun getDiffUtil(): DiffUtil.ItemCallback<I> = object : DiffUtil.ItemCallback<I>() {

        override fun areItemsTheSame(oldItem: I, newItem: I) = oldItem == newItem

        override fun areContentsTheSame(oldItem: I, newItem: I) = oldItem.equals(newItem)

        override fun getChangePayload(oldItem: I, newItem: I): Any? {
            if (oldItem.payload != newItem.payload) return newItem.payload
            return super.getChangePayload(oldItem, newItem)
        }
    }
}