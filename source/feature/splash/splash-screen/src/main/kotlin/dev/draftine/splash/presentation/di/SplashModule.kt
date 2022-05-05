package dev.draftine.splash.presentation.di

import dev.draftine.splash.presentation.view.SplashFragment
import dev.draftine.splash.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    scope<SplashFragment> {

        viewModel {
            SplashViewModel(
                splashNavigator = get()
            )
        }
    }
}