package dev.draftine.imagetape.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.imagetape.data.api.ImageTapeService
import dev.draftine.imagetape.data.mapper.ImageTapeResponseMapper
import dev.draftine.imagetape.data.repository.ImageTapeRepository
import dev.draftine.imagetape.domain.interactor.ImageTapeInteractor
import org.koin.dsl.module

@KoinModule
val imageTapeModule = module {

    single<ImageTapeService> { ImageTapeService.Provider(get(), get()).provide() }
    factory { ImageTapeRepository(get(), get()) }
    factory { ImageTapeResponseMapper() }
    factory { ImageTapeInteractor(get()) }
}