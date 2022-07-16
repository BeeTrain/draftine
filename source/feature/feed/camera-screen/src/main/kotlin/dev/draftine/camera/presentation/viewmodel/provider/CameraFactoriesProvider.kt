package dev.draftine.camera.presentation.viewmodel.provider

import android.net.Uri
import dev.draftine.camera.presentation.model.CameraResultMessage
import dev.draftine.camera.presentation.model.PermissionMessage
import dev.draftine.camera.presentation.model.PhotoButtonState
import dev.draftine.camera.presentation.model.VideoButtonState
import dev.draftine.camera.presentation.viewmodel.factory.CameraPermissionsFactory
import dev.draftine.camera.presentation.viewmodel.factory.CameraPermissionsMessageFactory
import dev.draftine.camera.presentation.viewmodel.factory.CameraResultMessageFactory
import dev.draftine.camera.presentation.viewmodel.factory.PhotoButtonStateFactory
import dev.draftine.camera.presentation.viewmodel.factory.VideoButtonStateFactory

class CameraFactoriesProvider(
    private val videoButtonStateFactory: VideoButtonStateFactory,
    private val photoButtonStateFactory: PhotoButtonStateFactory,
    private val cameraPermissionsFactory: CameraPermissionsFactory,
    private val permissionsMessageFactory: CameraPermissionsMessageFactory,
    private val cameraResultMessageFactory: CameraResultMessageFactory
) {

    fun createPhotoDefaultState(): PhotoButtonState {
        return photoButtonStateFactory.createDefaultState()
    }

    fun createVideoDefaultState(): VideoButtonState {
        return videoButtonStateFactory.createDefaultState()
    }

    fun createBeforeStartedState(): VideoButtonState {
        return videoButtonStateFactory.createBeforeStartedState()
    }

    fun createStartedState(): VideoButtonState {
        return videoButtonStateFactory.createStartedState()
    }

    fun createCameraPermissions(): Array<String> {
        return cameraPermissionsFactory.createCameraPermissions()
    }

    fun createRationaleMessage(): PermissionMessage {
        return permissionsMessageFactory.createRationaleMessage()
    }

    fun createDeniedMessage(): PermissionMessage {
        return permissionsMessageFactory.createDeniedMessage()
    }

    fun createPhotoSavedMessage(photoUri: Uri?): CameraResultMessage {
        return cameraResultMessageFactory.createPhotoSavedMessage(photoUri)
    }

    fun createPhotoSaveErrorMessage(cause: Exception): CameraResultMessage {
        return cameraResultMessageFactory.createPhotoSaveErrorMessage(cause)
    }

    fun createVideoSavedMessage(videoUri: Uri?): CameraResultMessage {
        return cameraResultMessageFactory.createVideoSavedMessage(videoUri)
    }

    fun createVideoSaveErrorMessage(cause: Throwable?): CameraResultMessage {
        return cameraResultMessageFactory.createVideoSaveErrorMessage(cause)
    }
}