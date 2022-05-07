package dev.draftine.feed.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.feed.presentation.view.FeedFragment
import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val feedModule = module {

    scope<FeedFragment> {

        viewModel { FeedViewModel() }
    }
}