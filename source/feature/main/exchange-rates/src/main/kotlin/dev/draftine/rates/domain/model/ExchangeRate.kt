package dev.draftine.rates.domain.model

import dev.draftine.utils.model.Currency
import org.joda.time.LocalDateTime
import java.math.BigDecimal

data class ExchangeRate(
    val currency: Currency,
    val rate: BigDecimal,
    val exchangeCurrency: Currency,
    val exchangeRate: BigDecimal,
    val date: LocalDateTime
)