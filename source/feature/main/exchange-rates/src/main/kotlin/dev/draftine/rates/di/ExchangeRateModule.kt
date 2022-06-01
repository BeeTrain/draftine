package dev.draftine.rates.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.rates.data.mapper.ExchangeRateUsdMapper
import dev.draftine.rates.data.repository.ExchangeRateUsdRepository
import dev.draftine.rates.domain.interactor.ExchangeRateInteractor
import dev.draftine.rates.domain.interactor.ExchangeRateInteractorImpl
import org.koin.dsl.module

@KoinModule
val exchangeRateModule = module {

    single { ExchangeRateUsdRepository(get(), get()) }
    single { ExchangeRateUsdMapper() }
    single<ExchangeRateInteractor> { ExchangeRateInteractorImpl(get()) }
}