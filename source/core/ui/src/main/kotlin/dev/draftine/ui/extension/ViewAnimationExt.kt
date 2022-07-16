package dev.draftine.ui.extension

import android.animation.ValueAnimator
import android.view.View

fun View.translate(from: Float, to: Float) {
    ValueAnimator.ofFloat(from, to).apply {
        addUpdateListener {
            translationY = it.animatedValue as Float
        }
        start()
    }
}

fun View.scale(from: Int, to: Int, duration: Long = 1000) {
    ValueAnimator.ofInt(from, to).apply {
        this.duration = duration
        addUpdateListener {
            val animatedValue = it.animatedValue as Int
            scaleX = animatedValue.toFloat()
            scaleY = animatedValue.toFloat()
        }
        start()
    }
}

fun View.fadeOut(fadeDuration: Long = 300, endAlpha: Float = 0f) {
    ValueAnimator.ofFloat(1f, endAlpha).apply {
        duration = fadeDuration
        addUpdateListener {
            val animatedValue = it.animatedValue as Float
            alpha = animatedValue
        }
        start()
    }
}

fun View.fadeIn(fadeDuration: Long = 300) {
    ValueAnimator.ofFloat(0f, 1f).apply {
        duration = fadeDuration
        addUpdateListener {
            val animatedValue = it.animatedValue as Float
            alpha = animatedValue
        }
        start()
    }
}