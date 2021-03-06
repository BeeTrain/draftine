package dev.draftine.ui.text

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import dev.draftine.ui.R
import dev.draftine.ui.extension.getAttrResId
import dev.draftine.ui.extension.getColorExt
import dev.draftine.ui.extension.setTextAppearanceExt

class TextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView)
        try {
            val type = TextType.values()[typedArray.getInteger(R.styleable.TextView_textType, TextType.NONE.ordinal)]
            if (type != TextType.NONE) {
                applyTextType(type)
            }
            val textColorRes = typedArray.getResourceId(R.styleable.TextView_android_textColor, 0)
            if (textColorRes != 0) {
                setTextColor(context.getColorExt(textColorRes))
            }
        } finally {
            typedArray.recycle()
        }
    }

    fun applyTextType(textType: TextType) {
        val textAppearanceAttr = when (textType) {
            TextType.NONE -> null
            TextType.HEADLINE_1 -> R.attr.styleAppearanceHeadline1
            TextType.HEADLINE_2 -> R.attr.styleAppearanceHeadline2
            TextType.HEADLINE_3 -> R.attr.styleAppearanceHeadline3
            TextType.HEADLINE_4 -> R.attr.styleAppearanceHeadline4
            TextType.HEADLINE_5 -> R.attr.styleAppearanceHeadline5
            TextType.HEADLINE_6 -> R.attr.styleAppearanceHeadline6
            TextType.SUBTITLE_1 -> R.attr.styleAppearanceSubtitle1
            TextType.SUBTITLE_2 -> R.attr.styleAppearanceSubtitle2
            TextType.BODY_1 -> R.attr.styleAppearanceBody1
            TextType.BODY_2 -> R.attr.styleAppearanceBody2
            TextType.BUTTON -> R.attr.styleAppearanceButton
            TextType.CAPTION -> R.attr.styleAppearanceCaption
            TextType.OVERLINE -> R.attr.styleAppearanceOverline
        }
        textAppearanceAttr?.let { setTextAppearanceExt(context.getAttrResId(it)) }
    }
}