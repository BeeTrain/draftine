package dev.draftine.app.di

import dev.draftine.app.presentation.di.applicationModule
import dev.draftine.main.presentation.di.mainModule
import dev.draftine.feed.presentation.di.feedModule
import dev.draftine.navigation.di.navigationModule
import dev.draftine.profile.presentation.di.profileModule
import dev.draftine.splash.presentation.di.splashModule

object AppModule {

    val modules = listOf(
        applicationModule,
        navigationModule,
        splashModule,
        mainModule,
        feedModule,
        profileModule,
    )
}