package dev.draftine.rates.presentation.view.adapter

import android.util.Log
import dev.draftine.rates.R
import dev.draftine.rates.presentation.model.ExchangeRateCardModel
import dev.draftine.ui.card.ExchangeRateCard
import dev.draftine.ui.recycler.Item
import dev.draftine.ui.recycler.RecyclerItem

class ExchangeRateRecyclerItem : RecyclerItem<ExchangeRateCard, ExchangeRateCardModel>() {

    override val layoutId = R.layout.exchange_rate_item

    override fun isRelativeItem(item: Item) = item is ExchangeRateCardModel

    override fun bind(view: ExchangeRateCard, item: ExchangeRateCardModel) {
        view.apply {
            currencyIcon = item.currencyIcon
            title = item.currencyTitle
            value = item.exchangeRateValue
        }
    }
}