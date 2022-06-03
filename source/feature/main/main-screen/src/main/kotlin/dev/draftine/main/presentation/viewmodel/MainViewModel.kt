package dev.draftine.main.presentation.viewmodel

import dev.draftine.advices.domain.interactor.AdviceInteractor
import dev.draftine.advices.presentation.model.AdviceModel
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.main.presentation.model.MainViewState
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.main.presentation.viewmodel.mapper.AdviceMapper
import dev.draftine.main.presentation.viewmodel.mapper.ExchangeRateMapper
import dev.draftine.rates.domain.interactor.ExchangeRateInteractor
import dev.draftine.rates.presentation.model.ExchangeRateCardModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val exchangeRateInteractor: ExchangeRateInteractor,
    private val adviceInteractor: AdviceInteractor,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val adviceMapper: AdviceMapper,
    private val mainNavigator: MainNavigator
) : BaseViewModel() {

    val contentState = MutableStateFlow<MainViewState>(MainViewState.Loading)

    fun openExchangeRateUrl(url: String) {
        mainNavigator.openExchangeRateUrl(url)
    }

    fun loadData() {
        launchLoadingErrorJob {
            val usdExchangeRateModel = loadUsdRubRateModel()
            val adviceModel = loadAdviceModel()

            contentState.emit(
                MainViewState.Data(
                    list = listOf(
                        usdExchangeRateModel,
                        adviceModel
                    )
                )
            )
        }
    }

    private suspend fun loadUsdRubRateModel(): ExchangeRateCardModel {
        return exchangeRateInteractor.loadUsdRubRate()
            .run { exchangeRateMapper.map(this) }
    }

    private suspend fun loadAdviceModel(): AdviceModel {
        return adviceInteractor.loadAdvice()
            .run { adviceMapper.map(this) }
    }
}