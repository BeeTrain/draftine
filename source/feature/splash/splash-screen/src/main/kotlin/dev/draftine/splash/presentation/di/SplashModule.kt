package dev.draftine.splash.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.splash.presentation.view.SplashFragment
import dev.draftine.splash.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val splashModule = module {

    scope<SplashFragment> {

        viewModel {
            SplashViewModel(
                splashNavigator = get()
            )
        }
    }
}