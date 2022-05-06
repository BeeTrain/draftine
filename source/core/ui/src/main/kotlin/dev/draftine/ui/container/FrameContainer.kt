package dev.draftine.ui.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import dev.draftine.ui.R
import dev.draftine.ui.extension.getAttrColor

class FrameContainer
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FrameContainer)
        try {
            typedArray.getResourceId(R.styleable.FrameContainer_android_background, 0).apply {
                applyBackground(this)
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun applyBackground(backgroundRes: Int) {
        when (backgroundRes) {
            0 -> setBackgroundColor(context.getAttrColor(R.attr.colorBackground))
            else -> setBackgroundResource(backgroundRes)
        }
    }
}