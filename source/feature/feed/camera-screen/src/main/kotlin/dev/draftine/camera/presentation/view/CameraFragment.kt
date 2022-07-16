package dev.draftine.camera.presentation.view

import android.os.Bundle
import android.view.View
import androidx.camera.view.PreviewView
import androidx.lifecycle.lifecycleScope
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.camera.R
import dev.draftine.camera.presentation.model.CameraResultMessage
import dev.draftine.camera.presentation.model.PermissionMessage
import dev.draftine.camera.presentation.viewmodel.CameraViewModel
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.px
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.image.ImageView
import dev.draftine.utils.camera.CameraHost
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val BUTTONS_BOTTOM_MARGIN = 16

class CameraFragment :
    BaseFragment<CameraViewModel>(R.layout.camera_fragment),
    CameraHost {

    override val viewModel: CameraViewModel by viewModel()

    private val capturePhotoButton by findView<ImageView>(R.id.camera_capture_photo_button)
    private val captureVideoButton by findView<ImageView>(R.id.camera_capture_video_button)
    override val previewView by findView<PreviewView>(R.id.camera_preview_view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        capturePhotoButton.setOnClickWithDelayListener { viewModel.takePhoto() }
        captureVideoButton.setOnClickWithDelayListener { viewModel.captureVideo() }

        viewModel.apply {
            capturePhotoButtonState.observeOnCreated(lifecycleScope) { state ->
                state.render(capturePhotoButton)
            }
            captureVideoButtonState.observeOnCreated(lifecycleScope) { state ->
                state.render(captureVideoButton)
            }
            permissionsGrantedEvent.observeOnCreated(lifecycleScope) {
                startCamera(this@CameraFragment)
            }
            permissionsMessageEvent.observeOnCreated(lifecycleScope) { permissionMessage ->
                renderPermissionMessage(permissionMessage)
            }
            cameraResultMessageEvent.observeOnCreated(lifecycleScope) { resultMessage ->
                renderCameraResultMessage(resultMessage)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkPermissions()
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
        capturePhotoButton.updateMargin(bottom = insetBottom + BUTTONS_BOTTOM_MARGIN.px)
        captureVideoButton.updateMargin(bottom = insetBottom + BUTTONS_BOTTOM_MARGIN.px)
    }

    private fun renderPermissionMessage(permissionMessage: PermissionMessage) {
        val rootView = view ?: return

        when (permissionMessage) {
            is PermissionMessage.Denied -> permissionMessage.render(rootView) { viewModel.openAppSettings() }
            is PermissionMessage.Rationale -> permissionMessage.render(rootView) { viewModel.checkPermissions() }
        }
    }

    private fun renderCameraResultMessage(cameraResultMessage: CameraResultMessage) {
        val rootView = view ?: return

        when (cameraResultMessage) {
            is CameraResultMessage.PhotoSaved -> cameraResultMessage.render(rootView) {
                viewModel.openImage(cameraResultMessage.fileUri)
            }
            is CameraResultMessage.VideoSaved -> cameraResultMessage.render(rootView) {
                viewModel.openVideo(cameraResultMessage.fileUri)
            }
            is CameraResultMessage.PhotoSaveError,
            is CameraResultMessage.VideoSaveError -> cameraResultMessage.render(rootView)
        }
    }
}