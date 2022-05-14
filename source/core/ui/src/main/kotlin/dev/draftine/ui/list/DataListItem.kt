package dev.draftine.ui.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import dev.draftine.ui.R
import dev.draftine.ui.container.ConstraintContainer
import dev.draftine.ui.extension.bind
import dev.draftine.ui.extension.setClickableExt
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.image.ImageView
import dev.draftine.ui.text.TextView

class DataListItem
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val dataListRoot by bind<ConstraintContainer>(R.id.data_list_item_root)
    private val dataListStartIcon by bind<ImageView>(R.id.data_list_item_start_icon)
    private val dataListTitle by bind<TextView>(R.id.data_list_item_title)
    private val dataListLabel by bind<TextView>(R.id.data_list_item_label)
    private val dataListCaption by bind<TextView>(R.id.data_list_item_caption)
    private val dataListEndIcon by bind<ImageView>(R.id.data_list_item_end_icon)

    var startIcon: Drawable? = null
        set(value) {
            field = value
            dataListStartIcon.setImageDrawable(value)
            dataListStartIcon.isVisible = value != null
        }

    var endIcon: Drawable? = null
        set(value) {
            field = value

            dataListEndIcon.setImageDrawable(value)
            dataListEndIcon.isVisible = value != null
        }

    var title: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            dataListTitle.text = value ?: ""
            dataListTitle.isVisible = isVisible
            updateViewsParams()
        }

    var label: String? = null
        set(value) {
            field = value
            dataListLabel.text = value ?: ""
            dataListLabel.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    var caption: String? = null
        set(value) {
            field = value
            dataListCaption.text = value ?: ""
            dataListCaption.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    var onClickListener: ((View?) -> Unit)? = null
        set(value) {
            field = value

            val isClickable = value != null
            dataListRoot.setClickableExt(isClickable)
            dataListRoot.setOnClickWithDelayListener {
                field?.invoke(it)
            }
        }

    init {
        inflate(context, R.layout.data_list_item, this)
        attrs?.let { applyAttributes(it) }
    }

    @Deprecated("Use onClickListener field instead.")
    override fun setOnClickListener(listener: OnClickListener?) = Unit

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DataListItem)
        try {
            startIcon = typedArray.getDrawable(R.styleable.DataListItem_startIcon)
            endIcon = typedArray.getDrawable(R.styleable.DataListItem_endIcon)
            title = typedArray.getString(R.styleable.DataListItem_title)
            label = typedArray.getString(R.styleable.DataListItem_label)
            caption = typedArray.getString(R.styleable.DataListItem_caption)
        } finally {
            typedArray.recycle()
        }
    }

    private fun updateViewsParams() {
        val isThreeLine = !title.isNullOrEmpty() && !label.isNullOrEmpty() && !caption.isNullOrEmpty()

        dataListStartIcon.updateLayoutParams<ConstraintLayout.LayoutParams> {
            verticalBias = if (isThreeLine) 0F else 0.5F
            topToTop = if (isThreeLine) dataListTitle.id else dataListRoot.id
        }
    }
}