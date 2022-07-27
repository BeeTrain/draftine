package dev.draftine.imageviewer.presentation

import android.widget.ImageView
import com.stfalcon.imageviewer.StfalconImageViewer
import dev.draftine.ui.extension.loadImage
import dev.draftine.ui.image.Image
import dev.draftine.ui.imageviewer.ImageViewerOverlay
import dev.draftine.utils.extension.indexOrNull
import dev.draftine.utils.lifecycle.ActivityContextProvider

private const val DEFAULT_POSITION = 0

class ImageViewer(
    private val activityContextProvider: ActivityContextProvider
) {

    fun openImageViewer(images: List<Image>, startImage: Image? = null) {
        val context = requireNotNull(activityContextProvider.activityContext)
        val overlayView = ImageViewerOverlay(context)
        val startPosition = images.indexOrNull(startImage) ?: DEFAULT_POSITION

        StfalconImageViewer.Builder(context, images, ::loadImage)
            .withStartPosition(startPosition)
            .withOverlayView(overlayView)
            .allowZooming(true)
            .allowSwipeToDismiss(true)
            .withHiddenStatusBar(false)
            .build()
            .also { imageViewer ->
                overlayView.setOnClickListener { imageViewer.dismiss() }
                imageViewer.show()
            }
    }

    private fun loadImage(imageView: ImageView, image: Image) {
        imageView.loadImage(image)
    }
}