package dev.draftine.imagetape.domain.interactor

import dev.draftine.imagetape.data.repository.ImageTapeRepository
import dev.draftine.imagetape.domain.model.ImageTapeItem

class ImageTapeInteractor(
    private val imageTapeRepository: ImageTapeRepository
) {

    suspend fun loadImageTape(): List<ImageTapeItem> {
        return imageTapeRepository.loadImages()
    }
}