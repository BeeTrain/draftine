package dev.draftine.imagetape.data.mapper

import dev.draftine.imagetape.data.model.ImageTapeResponse
import dev.draftine.imagetape.domain.model.ImageTapeItem

class ImageTapeResponseMapper {

    fun map(imageTapeResponse: ImageTapeResponse): ImageTapeItem {
        return ImageTapeItem(
            imageTapeResponse.id,
            imageTapeResponse.downloadUrl
        )
    }
}