package dev.draftine.ui.extension

import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat

fun TextView.setTextAppearanceExt(@StyleRes resId: Int) {
    TextViewCompat.setTextAppearance(this, resId)
}