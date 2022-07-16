package dev.draftine.camera.presentation.viewmodel.factory

import dev.draftine.camera.presentation.model.PermissionMessage
import dev.draftine.camera.presentation.viewmodel.provider.CameraResourcesProvider

class CameraPermissionsMessageFactory(private val resourcesProvider: CameraResourcesProvider) {

    fun createRationaleMessage(): PermissionMessage {
        return PermissionMessage.Rationale(
            resourcesProvider.cameraPermissionsRationaleMessage,
            resourcesProvider.requestPermissionsButtonTitle
        )
    }

    fun createDeniedMessage(): PermissionMessage {
        return PermissionMessage.Denied(
            resourcesProvider.cameraPermissionsDeniedMessage,
            resourcesProvider.openAppSettingsButtonTitle
        )
    }
}