package dev.draftine.ui.container

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.draftine.ui.R
import dev.draftine.ui.extension.getAttrResId

class PullToRefreshContainer
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {

    init {
        setColorSchemeResources(context.getAttrResId(R.attr.colorAccent))
        setProgressBackgroundColorSchemeResource(context.getAttrResId(R.attr.colorSurface))
    }

    fun setLoading(isLoading: Boolean) {
        isRefreshing = isLoading
    }

    fun isLoading(): Boolean {
        return isRefreshing
    }
}