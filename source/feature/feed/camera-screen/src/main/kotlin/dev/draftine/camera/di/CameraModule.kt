package dev.draftine.camera.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.camera.presentation.view.CameraFragment
import dev.draftine.camera.presentation.viewmodel.CameraViewModel
import dev.draftine.camera.presentation.viewmodel.factory.CameraPermissionsFactory
import dev.draftine.camera.presentation.viewmodel.factory.CameraPermissionsMessageFactory
import dev.draftine.camera.presentation.viewmodel.factory.CameraResultMessageFactory
import dev.draftine.camera.presentation.viewmodel.factory.PhotoButtonStateFactory
import dev.draftine.camera.presentation.viewmodel.factory.VideoButtonStateFactory
import dev.draftine.camera.presentation.viewmodel.provider.CameraFactoriesProvider
import dev.draftine.camera.presentation.viewmodel.provider.CameraResourcesProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@KoinModule
val cameraModule = module {

    scope<CameraFragment> {

        viewModel {
            CameraViewModel(
                permissionsManager = get(),
                cameraController = get(),
                cameraFactoriesProvider = get(),
                cameraNavigator = get()
            )
        }

        factory { CameraResourcesProvider(get()) }

        factory { CameraFactoriesProvider(get(), get(), get(), get(), get()) }

        factory { VideoButtonStateFactory(get()) }

        factory { CameraPermissionsFactory() }

        factory { CameraPermissionsMessageFactory(get()) }

        factory { CameraResultMessageFactory(get()) }

        factory { PhotoButtonStateFactory(get()) }
    }
}