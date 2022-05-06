package dev.draftine.arch.extension

import android.app.Activity
import android.view.View
import androidx.core.view.ViewCompat

fun interface OnSystemInsetsChangedListener {

    fun onApplySystemInsets(insetTop: Int, insetBottom: Int)
}

fun Activity.setupWindowInsets(listener: OnSystemInsetsChangedListener) {
    removeSystemInsets(window.decorView, listener)
}

fun removeSystemInsets(view: View, listener: OnSystemInsetsChangedListener) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
        listener.onApplySystemInsets(insets.systemWindowInsetTop, insets.systemWindowInsetBottom)
        ViewCompat.onApplyWindowInsets(
            view,
            insets.replaceSystemWindowInsets(insets.systemWindowInsetLeft, 0, insets.systemWindowInsetRight, 0)
        )
    }
}