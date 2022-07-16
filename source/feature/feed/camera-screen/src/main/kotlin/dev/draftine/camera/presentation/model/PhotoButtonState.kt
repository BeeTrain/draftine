package dev.draftine.camera.presentation.model

import dev.draftine.ui.image.Image
import dev.draftine.ui.image.ImageView

sealed class PhotoButtonState(open val icon: Image? = null) {

    abstract fun render(button: ImageView)

    class Default(override val icon: Image) : PhotoButtonState(icon) {

        override fun render(button: ImageView) {
            button.apply {
                loadImage(icon)
                isEnabled = true
            }
        }
    }
}