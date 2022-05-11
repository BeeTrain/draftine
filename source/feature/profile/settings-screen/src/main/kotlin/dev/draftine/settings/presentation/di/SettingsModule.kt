package dev.draftine.settings.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.settings.presentation.view.SettingsFragment
import dev.draftine.settings.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val settingsModule = module {

    scope<SettingsFragment> {

        viewModel {
            SettingsViewModel(
                settingsNavigator = get()
            )
        }
    }
}