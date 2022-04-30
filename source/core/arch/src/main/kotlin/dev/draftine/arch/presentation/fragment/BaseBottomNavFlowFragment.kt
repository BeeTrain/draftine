package dev.draftine.arch.presentation.fragment

import androidx.annotation.NavigationRes
import dev.draftine.arch.presentation.fragment.BaseFlowFragment

abstract class BaseBottomNavFlowFragment(@NavigationRes private val navGraphId: Int) : BaseFlowFragment() {

    override fun onBackPressed() {
        if (navController.navigateUp()) {
            return
        }

        (parentFragment as BaseFlowFragment).onBackPressed()
    }

    override fun getGraphResId() = navGraphId
}