package dev.draftine.app.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.app.presentation.view.ApplicationActivity
import dev.draftine.app.presentation.viewmodel.ApplicationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val applicationModule = module {

    scope<ApplicationActivity> {

        viewModel { ApplicationViewModel() }
    }
}