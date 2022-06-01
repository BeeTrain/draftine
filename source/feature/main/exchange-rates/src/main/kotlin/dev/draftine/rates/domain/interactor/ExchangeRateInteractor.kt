package dev.draftine.rates.domain.interactor

import dev.draftine.rates.domain.model.ExchangeRate

interface ExchangeRateInteractor {

    suspend fun loadUsdRubRate(): ExchangeRate
}