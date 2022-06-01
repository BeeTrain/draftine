package dev.draftine.rates.presentation.model

import dev.draftine.ui.image.Image
import dev.draftine.ui.recycler.Item

data class ExchangeRateCardModel(
    val currencyIcon: Image,
    val currencyTitle: String,
    val exchangeRateValue: String
) : Item()