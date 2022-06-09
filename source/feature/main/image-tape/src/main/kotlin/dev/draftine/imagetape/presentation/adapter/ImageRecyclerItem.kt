package dev.draftine.imagetape.presentation.adapter

import dev.draftine.imagetape.R
import dev.draftine.imagetape.presentation.model.TapeImage
import dev.draftine.ui.extension.setClickableExt
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.image.Image
import dev.draftine.ui.image.ImageView
import dev.draftine.ui.image.UrlImage
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class ImageRecyclerItem(
    private val onImageClick: ((Image) -> Unit)?
) : RecyclerItem<ImageView, TapeImage>() {

    override val layoutId = R.layout.image_item

    override fun isRelativeItem(item: Item) = item is TapeImage

    override fun bind(view: ImageView, item: TapeImage) {
        view.apply {
            val image = UrlImage(item.url)

            loadImage(image)
            setClickableExt(true)
            setOnClickWithDelayListener { onImageClick?.invoke(image) }
        }
    }
}