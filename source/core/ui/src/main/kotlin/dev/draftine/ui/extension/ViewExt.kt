package dev.draftine.ui.extension

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.Px
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

const val DEFAULT_TIME_OUT = 350L
const val SNACKBAR_TEXT_MAX_LINES = 5

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    val initialPadding = this.recordInitialPadding()
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

fun View.recordInitialPadding() = Rect(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

@ColorInt
fun View.getAttrColor(@AttrRes attr: Int) = context.getAttrColor(attr)

fun View.getAttrResId(@AttrRes attr: Int) = context.getAttrResId(attr)

fun View.getFontExt(@FontRes id: Int) = context.getFontExt(id)

fun View.getDrawableExt(@DrawableRes id: Int) = context.getDrawableExt(id)

fun View.getColorExt(@ColorRes id: Int) = context.getColorExt(id)

fun View.startAnimation(@AnimRes anim: Int) = startAnimation(AnimationUtils.loadAnimation(context, anim))

fun View.setOnClickWithDelayListener(delayMs: Long = DEFAULT_TIME_OUT, action: ((View) -> Unit)?) {
    isClickable = if (action != null) {
        setOnClickListener(getOnClickWithDelayListener(delayMs, action))
        true
    } else {
        setOnClickListener(null)
        false
    }
}

fun View.setClickableExt(clickable: Boolean) {
    this.apply {
        isClickable = clickable
        isFocusable = clickable

        if (clickable) {
            addRipple()
        } else {
            setBackgroundResource(0)
        }
    }
}

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}

private fun getOnClickWithDelayListener(delayMs: Long, action: (View) -> Unit): View.OnClickListener {
    return object : View.OnClickListener {

        private var lastClickTime = 0L

        override fun onClick(v: View) {
            val currentTime = SystemClock.uptimeMillis()
            if (lastClickTime == 0L || currentTime - lastClickTime > delayMs) {
                lastClickTime = currentTime
                action(v)
            }
        }
    }
}

fun View.updateMargin(
    @Px left: Int = marginLeft,
    @Px top: Int = marginTop,
    @Px right: Int = marginRight,
    @Px bottom: Int = marginBottom
) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        updateMargins(left, top, right, bottom)
    }
}

fun View.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(text, duration)
}

fun View.doOnDoubleClick(interval: Int = 300, onDoubleClick: (View) -> Unit) {
    val safeClickListener = DoubleClickListener(interval) {
        onDoubleClick(it)
    }
    setOnClickListener(safeClickListener)
}

class DoubleClickListener(
    private val defaultInterval: Int,
    private val onDoubleClick: (View) -> Unit
) : View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < defaultInterval) {
            onDoubleClick(v)
            lastClickTime = 0
        }
        lastClickTime = clickTime
    }
}

@Suppress("LongParameterList")
fun View.snackbar(
    message: String,
    actionText: String? = null,
    onActionClick: ((View) -> Unit)? = null,
    @ColorInt backgroundColor: Int? = null,
    duration: Int = BaseTransientBottomBar.LENGTH_SHORT,
    maxLines: Int = SNACKBAR_TEXT_MAX_LINES
) {
    Snackbar.make(this, message, duration).apply {
        view.translationY = (-context.navigationBarSize).toFloat()

        val snackTextView = findViewById<TextView?>(com.google.android.material.R.id.snackbar_text)
        snackTextView?.maxLines = maxLines

        if (backgroundColor != null) {
            setBackgroundTint(backgroundColor)
        }
        if (actionText != null) {
            setAction(actionText, onActionClick)
        }
        show()
    }
}