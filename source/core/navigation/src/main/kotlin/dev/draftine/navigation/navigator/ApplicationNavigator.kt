package dev.draftine.navigation.navigator

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import dev.draftine.navigation.R
import dev.draftine.profile.presentation.navigation.ProfileNavigator
import dev.draftine.profile.presentation.view.ProfileFragmentDirections
import dev.draftine.settings.presentation.navigation.SettingsNavigator
import dev.draftine.settings.presentation.view.SettingsFragmentDirections
import dev.draftine.splash.presentation.navigation.SplashNavigator
import dev.draftine.splash.presentation.view.SplashFragmentDirections
import dev.draftine.ui.extension.setFadeAnim
import dev.draftine.ui.extension.setHorizontalFullInAnim
import dev.draftine.ui.extension.setModalFullAnim

class ApplicationNavigator :
    AppNavigator,
    SplashNavigator,
    ProfileNavigator,
    SettingsNavigator {

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
            navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splash, true)
                .setHorizontalFullInAnim()
                .build()
        )
    }

    override fun fromProfileToSettings() {
        appNavController?.navigate(
            action = ProfileFragmentDirections.openSettings(),
            navOptions = NavOptions.Builder()
                .setModalFullAnim()
                .build()
        )
    }

    override fun closeSettings() {
        appNavController?.popBackStack()
    }

    override fun openAboutApp() {
        appNavController?.navigate(
            action = SettingsFragmentDirections.openAboutApp(),
            navOptions = NavOptions.Builder()
                .setHorizontalFullInAnim()
                .setPopUpTo(R.id.profile, false)
                .build()
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