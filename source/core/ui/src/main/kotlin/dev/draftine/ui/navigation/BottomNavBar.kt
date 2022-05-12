package dev.draftine.ui.navigation

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.draftine.ui.R
import dev.draftine.ui.extension.getAttrColor
import dev.draftine.ui.extension.getColorExt

class BottomNavBar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    init {
        applyAppTheme()
    }

    private fun applyAppTheme() {
        val surfaceColor = context.getAttrColor(R.attr.colorSurface)
        val unselectedColor = context.getColorExt(R.color.grey_700)
        val selectedColor = context.getAttrColor(R.attr.colorSecondary)
        val itemColorList = ColorStateList(
            arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)),
            intArrayOf(unselectedColor, selectedColor)
        )

        setBackgroundColor(surfaceColor)
        itemTextColor = itemColorList
        itemIconTintList = itemColorList
    }
}