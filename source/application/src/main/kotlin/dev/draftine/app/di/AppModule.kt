package dev.draftine.app.di

import dev.draftine.app.presentation.di.applicationModule
import dev.draftine.navigation.app.di.navigationModule

object AppModule {

    val modules = listOf(
        applicationModule,
        navigationModule,
    )
}