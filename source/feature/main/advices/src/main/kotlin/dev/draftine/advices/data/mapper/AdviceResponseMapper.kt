package dev.draftine.advices.data.mapper

import dev.draftine.advices.data.model.AdviceResponse
import dev.draftine.advices.domain.model.Advice

class AdviceResponseMapper {

    fun map(response: AdviceResponse): Advice {
        return Advice(
            id = response.slip.id,
            text = response.slip.advice
        )
    }
}