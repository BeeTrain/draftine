package dev.draftine.ui.imageviewer

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import dev.draftine.ui.R
import dev.draftine.ui.extension.addCircleRipple
import dev.draftine.ui.extension.doOnApplyWindowInsets
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.image.ImageView

class ImageViewerOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.image_viewer_overlay, this)
        setBackgroundColor(Color.TRANSPARENT)

        doOnApplyWindowInsets { view, insets, initialPadding ->
            val params = (view as ImageViewerOverlay).layoutParams as FrameLayout.LayoutParams
            params.topMargin = initialPadding.top + insets.systemWindowInsetTop
            params.bottomMargin = initialPadding.bottom + insets.systemWindowInsetBottom
            layoutParams = params
            insets
        }
    }

    fun setOnClickListener(onCloseClick: () -> Unit) {
        findViewById<ImageView>(R.id.image_viewer_overlay_close_icon).apply {
            setOnClickWithDelayListener { onCloseClick.invoke() }
            addCircleRipple()
        }
    }
}
