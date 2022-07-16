package dev.draftine.camera.presentation.viewmodel.factory

import dev.draftine.camera.presentation.model.PhotoButtonState
import dev.draftine.camera.presentation.viewmodel.provider.CameraResourcesProvider

class PhotoButtonStateFactory(private val resourcesProvider: CameraResourcesProvider) {

    fun createDefaultState(): PhotoButtonState {
        return PhotoButtonState.Default(resourcesProvider.capturePhotoIcon)
    }
}