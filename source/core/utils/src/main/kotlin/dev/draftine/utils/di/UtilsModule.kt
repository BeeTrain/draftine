package dev.draftine.utils.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.utils.camera.CameraController
import dev.draftine.utils.info.SystemInfoManager
import dev.draftine.utils.lifecycle.ActivityContextProvider
import dev.draftine.utils.lifecycle.ActivityLifecycler
import dev.draftine.utils.permissions.PermissionsManager
import dev.draftine.utils.resources.ResourcesProvider
import dev.draftine.utils.theme.AppThemeApplier
import dev.draftine.utils.theme.api.ThemeService
import dev.draftine.utils.theme.api.ThemeStorage
import dev.draftine.utils.theme.mapper.AppThemeMapper
import dev.draftine.utils.theme.repository.AppThemeStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@KoinModule
val utilsModule = module {

    single { SystemInfoManager(androidContext()) }
    single { ResourcesProvider(get()) }
    single(createdAtStart = true) { ActivityContextProvider(androidApplication()) }
    single(createdAtStart = true) { ActivityLifecycler(androidApplication()) }
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
    single { PermissionsManager(get()) }
    single { CameraController(get(), get()) }
}