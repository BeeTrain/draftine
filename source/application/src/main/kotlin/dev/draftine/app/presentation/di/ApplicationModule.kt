package dev.draftine.app.presentation.di

import dev.draftine.app.presentation.viewmodel.ApplicationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    viewModel { ApplicationViewModel() }
}