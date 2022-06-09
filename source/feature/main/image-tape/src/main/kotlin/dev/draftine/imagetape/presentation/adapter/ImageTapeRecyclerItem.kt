package dev.draftine.imagetape.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.draftine.imagetape.R
import dev.draftine.imagetape.presentation.model.ImageTapeModel
import dev.draftine.ui.extension.unsafeLazy
import dev.draftine.ui.image.Image
import dev.draftine.ui.list.RecyclerView
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerAdapter
import dev.draftine.ui.recycler.RecyclerItem
import dev.draftine.ui.recycler.snap.StartSnapper
import androidx.recyclerview.widget.RecyclerView as AndroidRecyclerView

class ImageTapeRecyclerItem(
    private val onImageClick: ((Image) -> Unit)?
) : RecyclerItem<View, ImageTapeModel>() {

    private val viewPool by unsafeLazy { AndroidRecyclerView.RecycledViewPool() }

    override val layoutId = R.layout.image_tape_item

    override fun isRelativeItem(item: Item) = item is ImageTapeModel

    override fun bind(view: View, item: ImageTapeModel) {
        view.findViewById<RecyclerView>(R.id.image_tape_recycler_view).let { recyclerView ->
            recyclerView.apply {
                layoutManager = createLayoutManager(context)
                setRecycledViewPool(viewPool)
                StartSnapper attachTo this
            }
            RecyclerAdapter.create(
                recyclerView = recyclerView,
                recyclerItems = listOf(ImageRecyclerItem(onImageClick))
            ).apply { submitList(item.items) }
        }
    }

    private fun createLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(
            context,
            AndroidRecyclerView.HORIZONTAL,
            false
        ).apply {
            initialPrefetchItemCount = 10
        }
    }
}