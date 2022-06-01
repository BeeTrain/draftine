package dev.draftine.navigation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.browser.navigation.BrowserNavigator
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.navigation.navigator.AppNavigator
import dev.draftine.navigation.navigator.ApplicationNavigator
import dev.draftine.navigation.presentation.BottomNavBarVisibilityManager
import dev.draftine.profile.presentation.navigation.ProfileNavigator
import dev.draftine.settings.presentation.navigation.SettingsNavigator
import dev.draftine.splash.presentation.navigation.SplashNavigator
import org.koin.dsl.module

@KoinModule
val navigationModule = module {

    single { BottomNavBarVisibilityManager() }

    single { ApplicationNavigator(get()) }

    single<AppNavigator> { get<ApplicationNavigator>() }

    single<SplashNavigator> { get<ApplicationNavigator>() }

    single<ProfileNavigator> { get<ApplicationNavigator>() }

    single<SettingsNavigator> { get<ApplicationNavigator>() }

    single<MainNavigator> { get<ApplicationNavigator>() }

    single<BrowserNavigator> { get<ApplicationNavigator>() }
}