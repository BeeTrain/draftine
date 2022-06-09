package dev.draftine.main.presentation.viewmodel.mapper

import dev.draftine.imagetape.domain.model.ImageTapeItem
import dev.draftine.imagetape.presentation.model.ImageTapeModel
import dev.draftine.imagetape.presentation.model.TapeImage

class ImageTapeMapper {

    fun map(imageTapeItems: List<ImageTapeItem>): ImageTapeModel {
        return ImageTapeModel(
            imageTapeItems.map { item ->
                TapeImage(
                    id = item.id,
                    url = item.url
                )
            }
        )
    }
}