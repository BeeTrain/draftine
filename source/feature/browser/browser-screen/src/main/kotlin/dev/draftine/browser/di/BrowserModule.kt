package dev.draftine.browser.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.browser.presentation.BrowserLauncher
import org.koin.dsl.module

@KoinModule
val browserModule = module {

    single { BrowserLauncher(get(), get()) }
}