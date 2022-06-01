package dev.draftine.rates.data.repository

import dev.draftine.network.data.rss.RssLoader
import dev.draftine.rates.data.mapper.ExchangeRateUsdMapper
import dev.draftine.rates.domain.model.ExchangeRate

private const val USD_RUB_EXCHANGE_RATE_URL = "https://usd.fxexchangerate.com/rub.xml"

internal class ExchangeRateUsdRepository(
    private val rssLoader: RssLoader,
    private val mapper: ExchangeRateUsdMapper
) {

    suspend fun getUsdRubRate(): ExchangeRate {
        return rssLoader.loadRss(USD_RUB_EXCHANGE_RATE_URL)
            .items
            .firstOrNull()
            ?.description
            .orEmpty()
            .let { mapper.map(it) }
    }
}