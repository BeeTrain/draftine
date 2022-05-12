package dev.draftine.settings.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.settings.data.repository.AppSettingsRepository
import dev.draftine.settings.domain.interactor.SettingsInteractor
import dev.draftine.settings.presentation.view.SettingsFragment
import dev.draftine.settings.presentation.viewmodel.SettingsViewModel
import dev.draftine.settings.presentation.viewmodel.mapper.SettingsMapper
import dev.draftine.settings.presentation.viewmodel.provider.SettingsResources
import dev.draftine.utils.resources.ResourcesProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val settingsModule = module {

    scope<SettingsFragment> {

        viewModel {
            SettingsViewModel(
                settingsInteractor = get(),
                settingsNavigator = get(),
                settingsMapper = get()
            )
        }
        scoped {
            SettingsResources(
                resourcesProvider = get()
            )
        }
        scoped {
            AppSettingsRepository(
                themeService = get()
            )
        }
        scoped {
            SettingsInteractor(
                settingsRepository = get<AppSettingsRepository>()
            )
        }
        scoped {
            SettingsMapper(
                settingsResources = get()
            )
        }
    }
}