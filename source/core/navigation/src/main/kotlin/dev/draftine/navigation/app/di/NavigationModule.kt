package dev.draftine.navigation.app.di

import dev.draftine.navigation.app.navigator.AppNavigator
import dev.draftine.navigation.app.navigator.ApplicationNavigator
import org.koin.dsl.module

val navigationModule = module {

    single { ApplicationNavigator() }

    single<AppNavigator> { get<ApplicationNavigator>() }
}