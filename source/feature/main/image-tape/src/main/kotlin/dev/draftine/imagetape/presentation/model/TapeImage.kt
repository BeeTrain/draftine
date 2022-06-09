package dev.draftine.imagetape.presentation.model

import dev.draftine.ui.recycler.Item

data class TapeImage(
    val id: String,
    val url: String
) : Item()