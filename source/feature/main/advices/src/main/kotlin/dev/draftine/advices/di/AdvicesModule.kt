package dev.draftine.advices.di

import dev.draftine.advices.data.api.AdvicesService
import dev.draftine.advices.data.mapper.AdviceResponseMapper
import dev.draftine.advices.data.repository.AdviceRepository
import dev.draftine.advices.domain.interactor.AdviceInteractor
import dev.draftine.annotation.processing.koin.annotation.KoinModule
import org.koin.dsl.module

@KoinModule
val advicesModule = module {

    single<AdvicesService> { AdvicesService.Provider(get(), get()).provide() }
    factory { AdviceRepository(get(), get()) }
    factory { AdviceResponseMapper() }
    factory { (AdviceInteractor(get())) }
}