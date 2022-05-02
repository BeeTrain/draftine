package dev.draftine.navigation.app.navigator

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import dev.draftine.splash.presentation.navigation.SplashNavigator
import dev.draftine.splash.presentation.view.SplashFragmentDirections
import dev.draftine.ui.extension.setFadeAnim
import dev.draftine.ui.extension.setHorizontalFullInAnim

class ApplicationNavigator:
    AppNavigator,
    SplashNavigator {

    private var appNavController: NavController? = null


    override fun bindAppController(navController: NavController) {
        appNavController = navController
    }

    override fun unbindAppController() {
        appNavController = null
    }

    override fun fromSplashToMain() {
        appNavController?.navigate(
            action = SplashFragmentDirections.actionOpenMain(),
            navOptions = NavOptions.Builder().setHorizontalFullInAnim().build()
        )
    }

    private fun NavController.navigate(
        action: NavDirections,
        args: Bundle? = null,
        navExtras: FragmentNavigator.Extras? = null,
        navOptions: NavOptions? = NavOptions.Builder().setFadeAnim().build()
    ) {
        navigate(action.actionId, args, navOptions, navExtras)
    }
}