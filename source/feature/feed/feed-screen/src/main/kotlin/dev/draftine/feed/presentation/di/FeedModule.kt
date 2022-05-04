package dev.draftine.feed.presentation.di

import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {

    viewModel { FeedViewModel() }
}