package dev.draftine.profile.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.profile.presentation.view.ProfileFragment
import dev.draftine.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val profileModule = module {

    scope<ProfileFragment> {

        viewModel {
            ProfileViewModel(
                profileNavigator = get()
            )
        }
    }
}