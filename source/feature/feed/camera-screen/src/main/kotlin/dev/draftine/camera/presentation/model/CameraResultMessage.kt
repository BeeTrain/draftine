package dev.draftine.camera.presentation.model

import android.net.Uri
import android.view.View
import dev.draftine.ui.extension.snackbar

sealed class CameraResultMessage(
    open val message: String,
    open val button: String?,
    open val fileUri: Uri?
) {

    abstract fun render(view: View, action: ((View) -> Unit)? = null)

    data class PhotoSaved(
        override val message: String,
        override val button: String,
        override val fileUri: Uri?
    ) : CameraResultMessage(message, button, fileUri) {

        override fun render(view: View, action: ((View) -> Unit)?) {
            view.snackbar(
                message,
                button,
                action
            )
        }
    }

    data class PhotoSaveError(
        override val message: String
    ) : CameraResultMessage(message, null, null) {

        override fun render(view: View, action: ((View) -> Unit)?) {
            view.snackbar(message)
        }
    }

    data class VideoSaved(
        override val message: String,
        override val button: String,
        override val fileUri: Uri?
    ) : CameraResultMessage(message, button, fileUri) {

        override fun render(view: View, action: ((View) -> Unit)?) {
            view.snackbar(
                message,
                button,
                action
            )
        }
    }

    data class VideoSaveError(
        override val message: String
    ) : CameraResultMessage(message, null, null) {

        override fun render(view: View, action: ((View) -> Unit)?) {
            view.snackbar(message)
        }
    }
}