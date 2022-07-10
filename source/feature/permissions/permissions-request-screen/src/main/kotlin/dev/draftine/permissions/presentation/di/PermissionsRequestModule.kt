package dev.draftine.permissions.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.permissions.PermissionsManager
import org.koin.dsl.module

@KoinModule
val permissionsRequestModule = module {

    single { PermissionsManager() }
}