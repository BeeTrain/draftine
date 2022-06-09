package dev.draftine.ui.extension

import android.widget.ImageView
import coil.load
import dev.draftine.ui.image.DrawableImage
import dev.draftine.ui.image.Image
import dev.draftine.ui.image.UrlImage

fun ImageView.loadImage(image: Image) {
    when(image) {
        is DrawableImage -> load(image.drawable)
        is UrlImage -> load(image.url)
    }
}