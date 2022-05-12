package dev.draftine.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.draftine.ui.list.RecyclerView

class RecyclerAdapter(
    private val recyclerItems: List<RecyclerItem<*, *>>,
) : ListAdapter<Item, RecyclerViewHolder<View, Item>>(
    RecyclerItemDiffUtil(recyclerItems)
) {

    companion object {

        fun create(
            recyclerView: RecyclerView,
            recyclerItems: List<RecyclerItem<*, *>> = emptyList()
        ): RecyclerAdapter {
            return RecyclerAdapter(recyclerItems).also {
                recyclerView.adapter = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<View, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return recyclerItems.find { it.layoutId == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let { it as RecyclerViewHolder<View, Item> }
            ?: throw IllegalArgumentException("View type not found: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder<View, Item>, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder<View, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerViewHolder<View, Item>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetached()
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return recyclerItems.find { it.isRelativeItem(item) }
            ?.layoutId
            ?: throw IllegalArgumentException("View type not found: $item")
    }
}