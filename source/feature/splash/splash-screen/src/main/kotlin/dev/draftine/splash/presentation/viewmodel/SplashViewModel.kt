package dev.draftine.splash.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.splash.presentation.navigation.SplashNavigator
import kotlinx.coroutines.delay

private const val DEFAULT_DELAY = 3000L
class SplashViewModel(
    private val splashNavigator: SplashNavigator
): BaseViewModel() {

    init {
        launchLoadingErrorJob {
            delay(DEFAULT_DELAY)
            splashNavigator.fromSplashToMain()
        }
    }
}