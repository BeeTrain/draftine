package dev.draftine.navigation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.browser.navigation.BrowserNavigator
import dev.draftine.camera.presentation.navigation.CameraNavigator
import dev.draftine.feed.presentation.navigation.FeedNavigator
import dev.draftine.imageviewer.navigation.ImageViewerNavigator
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

    single { ApplicationNavigator(get(), get(), get()) }

    single<AppNavigator> { get<ApplicationNavigator>() }

    single<SplashNavigator> { get<ApplicationNavigator>() }

    single<ProfileNavigator> { get<ApplicationNavigator>() }

    single<SettingsNavigator> { get<ApplicationNavigator>() }

    single<MainNavigator> { get<ApplicationNavigator>() }

    single<BrowserNavigator> { get<ApplicationNavigator>() }

    single<ImageViewerNavigator> { get<ApplicationNavigator>() }

    single<FeedNavigator> { get<ApplicationNavigator>() }

    single<CameraNavigator> { get<ApplicationNavigator>() }
}