package dev.draftine.advices.data.repository

import dev.draftine.advices.data.api.AdvicesService
import dev.draftine.advices.data.mapper.AdviceResponseMapper
import dev.draftine.advices.domain.model.Advice

class AdviceRepository(
    private val advicesService: AdvicesService,
    private val responseMapper: AdviceResponseMapper
) {

    suspend fun getAdvice(): Advice {
        return advicesService.getAdvice()
            .run { responseMapper.map(this) }
    }
}