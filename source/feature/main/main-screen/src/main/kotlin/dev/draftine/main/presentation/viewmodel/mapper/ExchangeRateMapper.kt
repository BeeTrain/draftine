package dev.draftine.main.presentation.viewmodel.mapper

import dev.draftine.rates.domain.model.ExchangeRate
import dev.draftine.rates.presentation.model.ExchangeRateCardModel
import dev.draftine.ui.image.DrawableImage
import dev.draftine.ui.image.Image
import dev.draftine.ui.image.Shape
import dev.draftine.utils.extension.format
import dev.draftine.utils.extension.isSame
import dev.draftine.utils.model.Currency
import dev.draftine.utils.resources.ResourcesProvider
import java.math.BigDecimal

private const val ICON_SHAPE_CORNER_RADIUS = 24F

class ExchangeRateMapper(
    private val resourcesProvider: ResourcesProvider
) {

    fun map(exchangeRate: ExchangeRate): ExchangeRateCardModel {
        return ExchangeRateCardModel(
            currencyIcon = getCurrencyIcon(exchangeRate.currency),
            currencyTitle = getCurrencyTitle(exchangeRate),
            exchangeRateValue = getExchangeRateValue(exchangeRate)
        )
    }

    private fun getCurrencyIcon(currency: Currency): Image {
        return DrawableImage(
            drawable = currency.flagRes?.let { resourcesProvider.getDrawable(it) },
            shape = Shape.Rounded(ICON_SHAPE_CORNER_RADIUS)
        )
    }

    private fun getCurrencyTitle(exchangeRate: ExchangeRate): String {
        return if (exchangeRate.rate.isSame(BigDecimal.ONE)) {
            exchangeRate.currency.shortName
        } else {
            exchangeRate.rate.format(exchangeRate.currency.symbol)
        }
    }

    private fun getExchangeRateValue(exchangeRate: ExchangeRate): String {
        return exchangeRate.exchangeRate.format(exchangeRate.exchangeCurrency.symbol)
    }
}