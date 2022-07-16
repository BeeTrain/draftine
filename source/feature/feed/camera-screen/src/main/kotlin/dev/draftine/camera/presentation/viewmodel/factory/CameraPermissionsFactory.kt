package dev.draftine.camera.presentation.viewmodel.factory

import android.Manifest
import android.os.Build

class CameraPermissionsFactory {

    fun createCameraPermissions(): Array<String> {
        return mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}