package dev.draftine.camera.presentation.model

import dev.draftine.ui.image.Image
import dev.draftine.ui.image.ImageView

sealed class VideoButtonState(open val icon: Image? = null) {

    abstract fun render(button: ImageView)

    class Default(override val icon: Image) : VideoButtonState(icon) {

        override fun render(button: ImageView) {
            button.apply {
                loadImage(icon)
                isEnabled = true
            }
        }
    }

    class BeforeStarted : VideoButtonState() {

        override fun render(button: ImageView) {
            button.isEnabled = false
        }
    }

    class Started(override val icon: Image) : VideoButtonState(icon) {

        override fun render(button: ImageView) {
            button.apply {
                loadImage(icon)
                isEnabled = true
            }
        }
    }
}