package dev.draftine.imageviewer.presentation

import android.widget.ImageView
import com.stfalcon.imageviewer.StfalconImageViewer
import dev.draftine.ui.extension.loadImage
import dev.draftine.ui.image.Image
import dev.draftine.ui.imageviewer.ImageViewerOverlay
import dev.draftine.utils.lifecycle.ActivityContextProvider

class ImageViewer(
    private val activityContextProvider: ActivityContextProvider
) {

    fun openImageViewer(images: List<Image>) {
        val context = requireNotNull(activityContextProvider.activityContext)
        val overlayView = ImageViewerOverlay(context)

        StfalconImageViewer.Builder(context, images, ::loadImage)
            .withOverlayView(overlayView)
            .allowZooming(true)
            .allowSwipeToDismiss(true)
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