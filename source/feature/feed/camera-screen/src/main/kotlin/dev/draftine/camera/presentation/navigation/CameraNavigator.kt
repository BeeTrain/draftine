package dev.draftine.camera.presentation.navigation

import android.net.Uri

interface CameraNavigator {

    fun openAppSettings()

    fun openImage(imageUri: Uri)

    fun openVideo(videoUri: Uri)
}