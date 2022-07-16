package dev.draftine.camera.presentation.viewmodel

import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.video.VideoRecordEvent
import dev.draftine.arch.lifecycle.SingleSharedFlow
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.camera.presentation.model.CameraResultMessage
import dev.draftine.camera.presentation.model.PermissionMessage
import dev.draftine.camera.presentation.navigation.CameraNavigator
import dev.draftine.camera.presentation.viewmodel.provider.CameraFactoriesProvider
import dev.draftine.ui.extension.unsafeLazy
import dev.draftine.utils.camera.CameraController
import dev.draftine.utils.camera.CameraHost
import dev.draftine.utils.permissions.PermissionsManager
import dev.draftine.utils.permissions.model.Permission
import kotlinx.coroutines.flow.MutableStateFlow

class CameraViewModel(
    private val permissionsManager: PermissionsManager,
    private val cameraController: CameraController,
    private val cameraFactoriesProvider: CameraFactoriesProvider,
    private val cameraNavigator: CameraNavigator
) : BaseViewModel() {

    val capturePhotoButtonState = MutableStateFlow(cameraFactoriesProvider.createPhotoDefaultState())
    val captureVideoButtonState = MutableStateFlow(cameraFactoriesProvider.createVideoDefaultState())
    val permissionsGrantedEvent = SingleSharedFlow<Boolean>()
    val permissionsMessageEvent = SingleSharedFlow<PermissionMessage>()
    val cameraResultMessageEvent = SingleSharedFlow<CameraResultMessage>()

    private val permissionsCallback by unsafeLazy { createPermissionsCallback() }
    private val cameraVideoCallback by unsafeLazy { createCameraVideoCallback() }
    private val cameraImageCallback by unsafeLazy { createCameraImageCallback() }

    fun checkPermissions() {
        launchLoadingErrorJob {
            if (isAllPermissionsGranted()) {
                permissionsGrantedEvent.emit(true)
            } else {
                permissionsManager.requestPermissions(
                    permissions = cameraFactoriesProvider.createCameraPermissions(),
                    callback = permissionsCallback
                )
            }
        }
    }

    fun startCamera(cameraHost: CameraHost) {
        cameraController.startCamera(cameraHost)
    }

    fun takePhoto() {
        cameraController.takePhoto(cameraImageCallback)
    }

    fun captureVideo() {
        cameraController.captureVideo(cameraVideoCallback)
    }

    fun openAppSettings() {
        cameraNavigator.openAppSettings()
    }

    fun openImage(imageUri: Uri?) {
        if (imageUri == null) return

        cameraNavigator.openImage(imageUri)
    }

    fun openVideo(videoUri: Uri?) {
        if (videoUri == null) return

        cameraNavigator.openVideo(videoUri)
    }

    private fun isAllPermissionsGranted(): Boolean {
        val requiredPermissions = cameraFactoriesProvider.createCameraPermissions()
        return permissionsManager.isGranted(requiredPermissions)
    }

    private fun createPermissionsCallback(): PermissionsManager.Callback {
        return object : PermissionsManager.Callback {

            override fun permissionsGranted(permissions: List<Permission>) {
                permissionsGrantedEvent.tryEmit(true)
            }

            override fun permissionsDenied(permissions: List<Permission>) {
                val message = cameraFactoriesProvider.createDeniedMessage()
                permissionsMessageEvent.tryEmit(message)
            }

            override fun showRationale(permissions: List<Permission>) {
                val message = cameraFactoriesProvider.createRationaleMessage()
                permissionsMessageEvent.tryEmit(message)
            }
        }
    }

    private fun createCameraImageCallback(): CameraController.ImageCallback {
        return object : CameraController.ImageCallback {

            override fun onSaved(output: ImageCapture.OutputFileResults) {
                val message = cameraFactoriesProvider.createPhotoSavedMessage(output.savedUri)
                cameraResultMessageEvent.tryEmit(message)
            }

            override fun onError(exception: ImageCaptureException) {
                val message = cameraFactoriesProvider.createPhotoSaveErrorMessage(exception)
                cameraResultMessageEvent.tryEmit(message)
            }
        }
    }

    private fun createCameraVideoCallback(): CameraController.VideoCallback {
        return object : CameraController.VideoCallback {

            override fun beforeStarted() {
                captureVideoButtonState.tryEmit(cameraFactoriesProvider.createBeforeStartedState())
            }

            override fun onStarted(recordEvent: VideoRecordEvent.Start) {
                captureVideoButtonState.tryEmit(cameraFactoriesProvider.createStartedState())
            }

            override fun onFinalized(recordEvent: VideoRecordEvent.Finalize) {
                if (recordEvent.hasError().not()) {
                    val outputUri = recordEvent.outputResults.outputUri
                    val message = cameraFactoriesProvider.createVideoSavedMessage(outputUri)
                    cameraResultMessageEvent.tryEmit(message)
                } else {
                    val message = cameraFactoriesProvider.createVideoSaveErrorMessage(recordEvent.cause)
                    cameraResultMessageEvent.tryEmit(message)
                }
                captureVideoButtonState.tryEmit(cameraFactoriesProvider.createVideoDefaultState())
            }
        }
    }
}