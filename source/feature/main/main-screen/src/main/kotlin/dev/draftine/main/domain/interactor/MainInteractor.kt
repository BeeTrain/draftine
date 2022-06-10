package dev.draftine.main.domain.interactor

import dev.draftine.advices.domain.interactor.AdviceInteractor
import dev.draftine.imagetape.domain.interactor.ImageTapeInteractor
import dev.draftine.rates.domain.interactor.ExchangeRateInteractor

class MainInteractor(
    private val exchangeRateInteractor: ExchangeRateInteractor,
    private val adviceInteractor: AdviceInteractor,
    private val imageTapeInteractor: ImageTapeInteractor
) {

    suspend fun loadData(): List<Any> {
        return listOf(
            exchangeRateInteractor.loadUsdRubRate(),
            adviceInteractor.loadAdvice(),
            imageTapeInteractor.loadImageTape()
        )
    }
}