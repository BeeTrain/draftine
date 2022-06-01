package dev.draftine.rates.data.mapper

import androidx.core.text.HtmlCompat
import dev.draftine.network.data.rss.model.ChannelItem
import dev.draftine.rates.domain.model.ExchangeRate
import dev.draftine.utils.EQUAL
import dev.draftine.utils.NEW_LINE
import dev.draftine.utils.SPACE
import dev.draftine.utils.model.Currency
import org.joda.time.LocalDateTime
import java.math.BigDecimal

private const val CURRENCIES_SEPARATOR = "$SPACE$EQUAL$SPACE"

internal class ExchangeRateUsdMapper {

    fun map(response: ChannelItem): ExchangeRate {
        val exchange = HtmlCompat.fromHtml(response.description.orEmpty(), HtmlCompat.FROM_HTML_MODE_LEGACY)
            .toString()
            .split(NEW_LINE)
            .first()
            .split(CURRENCIES_SEPARATOR)

        val date = LocalDateTime.now()
        val link = response.link

        val currencyValues = exchange.first().split(SPACE)
        val currencyRate = BigDecimal(currencyValues.first())
        val currency = Currency.fromShortName(currencyValues.last())

        val exchangeCurrencyValues = exchange.last().split(SPACE)
        val exchangeRate = BigDecimal(exchangeCurrencyValues.first())
        val exchangeCurrency = Currency.fromShortName(exchangeCurrencyValues.last())

        return ExchangeRate(
            currency,
            currencyRate,
            exchangeCurrency,
            exchangeRate,
            date,
            link
        )
    }
}