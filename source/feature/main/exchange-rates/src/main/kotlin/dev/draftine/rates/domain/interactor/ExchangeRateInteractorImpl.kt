package dev.draftine.rates.domain.interactor

import dev.draftine.rates.data.repository.ExchangeRateUsdRepository
import dev.draftine.rates.domain.model.ExchangeRate

internal class ExchangeRateInteractorImpl(
    private val exchangeRateUsdRepository: ExchangeRateUsdRepository
) : ExchangeRateInteractor {

    override suspend fun loadUsdRubRate(): ExchangeRate {
        return exchangeRateUsdRepository.getUsdRubRate()
    }
}