package dev.draftine.advices.domain.interactor

import dev.draftine.advices.data.repository.AdviceRepository
import dev.draftine.advices.domain.model.Advice

class AdviceInteractor(
    private val adviceRepository: AdviceRepository
) {

    suspend fun loadAdvice(): Advice {
        return adviceRepository.getAdvice()
    }
}