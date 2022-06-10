package dev.draftine.main.presentation.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.main.domain.interactor.MainInteractor
import dev.draftine.main.presentation.view.MainFragment
import dev.draftine.main.presentation.viewmodel.MainViewModel
import dev.draftine.main.presentation.viewmodel.mapper.AdviceMapper
import dev.draftine.main.presentation.viewmodel.mapper.ExchangeRateMapper
import dev.draftine.main.presentation.viewmodel.mapper.ImageTapeMapper
import dev.draftine.main.presentation.viewmodel.mapper.MainMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val mainModule = module {

    scope<MainFragment> {

        viewModel {
            MainViewModel(
                mainInteractor = get(),
                mainMapper = get(),
                mainNavigator = get(),
            )
        }
        factory { MainMapper(get(), get(), get()) }
        factory { MainInteractor(get(), get(), get()) }
        factory { ExchangeRateMapper(get()) }
        factory { AdviceMapper() }
        factory { ImageTapeMapper() }
    }
}