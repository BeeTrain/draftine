package dev.draftine.profile.presentation.di

import dev.draftine.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {

    viewModel { ProfileViewModel() }
}