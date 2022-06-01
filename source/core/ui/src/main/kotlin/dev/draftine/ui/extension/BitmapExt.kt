package dev.draftine.ui.extension

import android.graphics.Bitmap

val Bitmap.safeConfig: Bitmap.Config
    get() = config ?: Bitmap.Config.ARGB_8888