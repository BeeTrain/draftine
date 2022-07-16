package dev.draftine.camera.presentation.model

import android.view.View
import dev.draftine.ui.extension.snackbar

sealed class PermissionMessage(
    open val message: String,
    open val button: String
) {

    abstract fun render(view: View, action: ((View) -> Unit))

    data class Rationale(
        override val message: String,
        override val button: String
        ): PermissionMessage(message, button) {

        override fun render(view: View, action: (View) -> Unit) {
            view.snackbar(
                message,
                button,
                action
            )
        }
    }

    data class Denied(
        override val message: String,
        override val button: String
    ): PermissionMessage(message, button) {

        override fun render(view: View, action: (View) -> Unit) {
            view.snackbar(
                message,
                button,
                action
            )
        }
    }
}