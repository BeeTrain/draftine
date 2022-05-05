package dev.draftine.navigation.di

import dev.draftine.navigation.navigator.AppNavigator
import dev.draftine.navigation.navigator.ApplicationNavigator
import dev.draftine.navigation.presentation.BottomNavBarVisibilityManager
import dev.draftine.splash.presentation.navigation.SplashNavigator
import org.koin.dsl.module

val navigationModule = module {

    single { BottomNavBarVisibilityManager() }

    single { ApplicationNavigator() }

    single<AppNavigator> { get<ApplicationNavigator>() }

    single<SplashNavigator> { get<ApplicationNavigator>() }
}