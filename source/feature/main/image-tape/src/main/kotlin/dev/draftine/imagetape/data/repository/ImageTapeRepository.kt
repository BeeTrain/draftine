package dev.draftine.imagetape.data.repository

import dev.draftine.imagetape.data.api.ImageTapeService
import dev.draftine.imagetape.data.mapper.ImageTapeResponseMapper
import dev.draftine.imagetape.domain.model.ImageTapeItem
import kotlin.random.Random

class ImageTapeRepository(
    private val imageTapeService: ImageTapeService,
    private val responseMapper: ImageTapeResponseMapper
) {

    suspend fun loadImages(): List<ImageTapeItem> {
        val page = Random.nextInt(1, 10 )
        return imageTapeService.getImages(page)
            .map { responseMapper.map(it) }
    }
}