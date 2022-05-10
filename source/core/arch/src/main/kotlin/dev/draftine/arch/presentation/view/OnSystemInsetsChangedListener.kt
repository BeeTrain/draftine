package dev.draftine.arch.presentation.view

fun interface OnSystemInsetsChangedListener {

    fun onApplySystemInsets(insetTop: Int, insetBottom: Int)
}