package dev.draftine.camera.presentation.viewmodel.factory

import android.net.Uri
import dev.draftine.camera.presentation.model.CameraResultMessage
import dev.draftine.camera.presentation.viewmodel.provider.CameraResourcesProvider

class CameraResultMessageFactory(private val resourcesProvider: CameraResourcesProvider) {

    fun createPhotoSavedMessage(photoUri: Uri?): CameraResultMessage {
        return CameraResultMessage.PhotoSaved(
            message = resourcesProvider.getPhotoSucceededMessage(photoUri?.toString().orEmpty()),
            button = resourcesProvider.openFileButtonTitle,
            fileUri = photoUri
        )
    }

    fun createPhotoSaveErrorMessage(cause: Exception): CameraResultMessage {
        return CameraResultMessage.PhotoSaveError(resourcesProvider.getPhotoFailedMessage(cause.message.orEmpty()))
    }

    fun createVideoSavedMessage(videoUri: Uri?): CameraResultMessage {
        return CameraResultMessage.VideoSaved(
            message = resourcesProvider.getVideoSucceededMessage(videoUri?.toString().orEmpty()),
            button = resourcesProvider.openFileButtonTitle,
            fileUri = videoUri
        )
    }

    fun createVideoSaveErrorMessage(cause: Throwable?): CameraResultMessage {
        return CameraResultMessage.VideoSaveError(resourcesProvider.getVideoFailedMessage(cause?.message.orEmpty()))
    }
}