package dev.draftine.data.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.data.storage.SimpleDataStorageProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@KoinModule
val dataModule = module {

    single { SimpleDataStorageProvider.provide(androidContext()) }
}