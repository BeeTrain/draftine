package dev.draftine.camera.presentation.viewmodel.provider

import dev.draftine.camera.R
import dev.draftine.ui.image.DrawableImage
import dev.draftine.utils.resources.ResourcesProvider

class CameraResourcesProvider(private val resourcesProvider: ResourcesProvider) {

    val capturePhotoIcon = DrawableImage(resourcesProvider.getDrawable(R.drawable.ic_camera))

    val startVideoIcon = DrawableImage(resourcesProvider.getDrawable(R.drawable.ic_video_start))

    val endVideoIcon = DrawableImage(resourcesProvider.getDrawable(R.drawable.ic_video_end))

    val cameraPermissionsDeniedMessage = resourcesProvider.getString(R.string.camera_permissions_denied_message)

    val cameraPermissionsRationaleMessage = resourcesProvider.getString(R.string.camera_permissions_rationale_message)

    val requestPermissionsButtonTitle = resourcesProvider.getString(R.string.camera_request_permissions_button_title)

    val openAppSettingsButtonTitle = resourcesProvider.getString(R.string.camera_open_app_settings_button_title)

    val openFileButtonTitle = resourcesProvider.getString(R.string.camera_open_file_button_title)

    fun getPhotoSucceededMessage(outputUri: String): String {
        return resourcesProvider.getString(R.string.photo_capture_succeeded_message, outputUri)
    }

    fun getPhotoFailedMessage(cause: String): String {
        return resourcesProvider.getString(R.string.photo_capture_failed_message, cause)
    }

    fun getVideoSucceededMessage(outputUri: String): String {
        return resourcesProvider.getString(R.string.video_capture_succeeded_message, outputUri)
    }

    fun getVideoFailedMessage(cause: String): String {
        return resourcesProvider.getString(R.string.video_capture_failed_message, cause)
    }
}