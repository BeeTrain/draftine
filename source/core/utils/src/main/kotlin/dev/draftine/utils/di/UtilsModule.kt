package dev.draftine.utils.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.utils.resources.ResourcesProvider
import dev.draftine.utils.theme.AppThemeApplier
import dev.draftine.utils.theme.api.ThemeService
import dev.draftine.utils.theme.api.ThemeStorage
import dev.draftine.utils.theme.mapper.AppThemeMapper
import dev.draftine.utils.theme.repository.AppThemeStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@KoinModule
val utilsModule = module {

    single { ResourcesProvider(androidContext()) }

    single<ThemeService>(createdAtStart = true) {
        AppThemeApplier(
            appThemeStorage = get(),
            appThemeMapper = get()
        )
    }
    single<ThemeStorage> {
        AppThemeStorage(
            simpleDataStorage = get(),
            appThemeMapper = get()
        )
    }
    factory { AppThemeMapper() }
}