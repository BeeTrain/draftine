package dev.draftine.main.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.main.presentation.model.MainViewState
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.main.presentation.viewmodel.mapper.ExchangeRateMapper
import dev.draftine.rates.domain.interactor.ExchangeRateInteractor
import dev.draftine.rates.presentation.model.ExchangeRateCardModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val exchangeRateInteractor: ExchangeRateInteractor,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val mainNavigator: MainNavigator
) : BaseViewModel() {

    val contentState = MutableStateFlow<MainViewState>(MainViewState.Loading)

    fun openExchangeRateUrl(url: String) {
        mainNavigator.openExchangeRateUrl(url)
    }

    fun loadData() {
        launchLoadingErrorJob {
            val usdExchangeRateModel = loadUsdRubRateModel()
            contentState.emit(
                MainViewState.Data(
                    list = listOf(
                        usdExchangeRateModel
                    )
                )
            )
        }
    }

    private suspend fun loadUsdRubRateModel(): ExchangeRateCardModel {
        return exchangeRateInteractor.loadUsdRubRate()
            .run { exchangeRateMapper.map(this) }
    }
}