package dev.draftine.main.presentation.viewmodel.mapper

import dev.draftine.imagetape.domain.model.ImageTapeItem
import dev.draftine.ui.image.Image
import dev.draftine.ui.image.UrlImage

class ImageTapeListMapper {

    fun map(imageTape: List<ImageTapeItem>): List<Image> {
        return imageTape.map { UrlImage(it.url) }
    }
}