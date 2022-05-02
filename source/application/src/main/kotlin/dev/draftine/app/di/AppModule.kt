package dev.draftine.app.di

import dev.draftine.app.presentation.di.applicationModule
import dev.draftine.main.presentation.di.mainModule
import dev.draftine.navigation.app.di.navigationModule
import dev.draftine.splash.presentation.di.splashModule

object AppModule {

    val modules = listOf(
        applicationModule,
        navigationModule,
        splashModule,
        mainModule,
    )
}