package dev.draftine.main.presentation.viewmodel.mapper

import dev.draftine.advices.domain.model.Advice
import dev.draftine.imagetape.domain.model.ImageTapeItem
import dev.draftine.rates.domain.model.ExchangeRate
import dev.draftine.ui.recycler.Item

class MainMapper(
    private val exchangeRateMapper: ExchangeRateMapper,
    private val adviceMapper: AdviceMapper,
    private val imageTapeMapper: ImageTapeMapper
) {

    fun map(dataItem: Any): Item? {
        return when (dataItem) {
            is ExchangeRate -> exchangeRateMapper.map(dataItem)
            is Advice -> adviceMapper.map(dataItem)
            is List<*> -> mapList(dataItem)
            else -> null
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T> mapList(dataItemList: List<T>): Item? {
        return when {
            ImageTapeItem::class is T -> imageTapeMapper.map(dataItemList as List<ImageTapeItem>)
            else -> null
        }
    }
}