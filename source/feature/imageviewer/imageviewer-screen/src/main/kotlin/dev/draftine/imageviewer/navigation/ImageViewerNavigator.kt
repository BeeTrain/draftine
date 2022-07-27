package dev.draftine.imageviewer.navigation

import dev.draftine.ui.image.Image

interface ImageViewerNavigator {

    fun openImageViewer(images: List<Image>, selectedImage: Image)
}