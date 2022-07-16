package dev.draftine.feed.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.feed.domain.interactor.FeedInteractor
import dev.draftine.feed.presentation.view.FeedFragment
import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import dev.draftine.feed.presentation.viewmodel.factory.FeedListItemFactory
import dev.draftine.feed.presentation.viewmodel.mapper.FeedListMapper
import dev.draftine.feed.presentation.viewmodel.provider.FeedResourcesProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val feedModule = module {

    scope<FeedFragment> {

        viewModel { FeedViewModel(get(), get(), get()) }

        factory { FeedInteractor() }

        factory { FeedListMapper(get()) }

        factory { FeedListItemFactory(get()) }

        factory { FeedResourcesProvider(get()) }
    }
}