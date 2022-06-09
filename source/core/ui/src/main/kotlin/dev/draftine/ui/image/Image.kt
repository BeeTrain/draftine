package dev.draftine.ui.image

import android.graphics.drawable.Drawable

sealed interface Image

data class DrawableImage(
    val drawable: Drawable?,
    val shape: Shape? = null
) : Image

data class UrlImage(
    val url: String,
    val shape: Shape? = null
) : Image