package dev.draftine.imagetape.presentation.model

import dev.draftine.ui.recycler.Item

data class ImageTapeModel(
    val items: List<TapeImage>
) : Item()