package dev.draftine.ui.extension

import android.content.res.Resources
import android.util.TypedValue

private const val DP_DENSITY = 160f

fun Float.pxToDp(): Float = this / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DP_DENSITY)

fun Int.pxToDp(): Float = this.toFloat().pxToDp()

fun Float.dpToPx(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics).toInt()
}

fun Int.dpToPx(): Int = this.toFloat().dpToPx()

fun Float.spToPx(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics).toInt()
}

fun Int.spToPx(): Int = this.toFloat().spToPx()

val Int.dp
    get() = this.pxToDp()

val Float.dp
    get() = this.pxToDp()

val Int.px
    get() = this.dpToPx()

val Float.px
    get() = this.dpToPx()