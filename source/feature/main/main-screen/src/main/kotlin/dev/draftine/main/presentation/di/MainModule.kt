package dev.draftine.main.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.main.presentation.view.MainFragment
import dev.draftine.main.presentation.viewmodel.MainViewModel
import dev.draftine.main.presentation.viewmodel.mapper.AdviceMapper
import dev.draftine.main.presentation.viewmodel.mapper.ExchangeRateMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val mainModule = module {

    scope<MainFragment> {

        viewModel {
            MainViewModel(
                exchangeRateInteractor = get(),
                adviceInteractor = get(),
                exchangeRateMapper = get(),
                adviceMapper = get(),
                mainNavigator = get()
            )
        }
        factory { ExchangeRateMapper(get()) }
        factory { AdviceMapper() }
    }
}