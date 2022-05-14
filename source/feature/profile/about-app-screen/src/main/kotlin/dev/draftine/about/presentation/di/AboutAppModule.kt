package dev.draftine.about.presentation.di

import dev.draftine.about.data.repository.AppInfoRepository
import dev.draftine.about.domain.interactor.AboutAppInteractor
import dev.draftine.about.domain.repository.AboutAppInfoRepository
import dev.draftine.about.presentation.view.AboutAppFragment
import dev.draftine.about.presentation.viewmodel.AboutAppViewModel
import dev.draftine.about.presentation.viewmodel.factory.AppInfoModelFactory
import dev.draftine.about.presentation.viewmodel.mapper.AppInfoMapper
import dev.draftine.about.presentation.viewmodel.provider.AboutAppResources
import dev.draftine.annotation.processing.koin.annotation.KoinModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val aboutAppModule = module {

    scope<AboutAppFragment> {

        viewModel {
            AboutAppViewModel(
                aboutAppInteractor = get(),
                appInfoMapper = get()
            )
        }
        scoped { AboutAppInteractor(get()) }
        scoped<AboutAppInfoRepository> { AppInfoRepository(get()) }
        scoped { AppInfoMapper(get()) }
        scoped { AppInfoModelFactory(get()) }
        scoped { AboutAppResources(get()) }
    }
}