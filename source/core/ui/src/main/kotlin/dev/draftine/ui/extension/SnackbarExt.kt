package dev.draftine.ui.extension

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setActionIcon(drawable: Drawable, listener: View.OnClickListener?): Snackbar {
    return this.apply {
        setAction(" ") {}
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)?.let {
            it.text = ""
            drawable.setTintMode(PorterDuff.Mode.SRC_ATOP)
            it.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            it.setOnClickListener(listener)
        }
    }
}