package dev.draftine.camera.presentation.viewmodel.factory

import dev.draftine.camera.presentation.model.VideoButtonState
import dev.draftine.camera.presentation.viewmodel.provider.CameraResourcesProvider

class VideoButtonStateFactory(private val resourcesProvider: CameraResourcesProvider) {

    fun createDefaultState(): VideoButtonState {
        return VideoButtonState.Default(resourcesProvider.startVideoIcon)
    }

    fun createBeforeStartedState(): VideoButtonState {
        return VideoButtonState.BeforeStarted()
    }

    fun createStartedState(): VideoButtonState {
        return VideoButtonState.Started(resourcesProvider.endVideoIcon)
    }
}