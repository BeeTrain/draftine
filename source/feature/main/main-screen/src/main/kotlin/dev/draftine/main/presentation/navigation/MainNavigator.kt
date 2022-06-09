package dev.draftine.main.presentation.navigation

import dev.draftine.ui.image.Image

interface MainNavigator {

    fun openExchangeRateUrl(url: String)

    fun openTapeImageInViewer(image: Image)
}