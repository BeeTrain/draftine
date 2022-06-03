package dev.draftine.main.presentation.viewmodel.mapper

import dev.draftine.advices.domain.model.Advice
import dev.draftine.advices.presentation.model.AdviceModel

class AdviceMapper {

    fun map(advice: Advice): AdviceModel {
        return AdviceModel(advice.text)
    }
}