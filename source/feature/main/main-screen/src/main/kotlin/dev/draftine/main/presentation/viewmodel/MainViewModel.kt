package dev.draftine.main.presentation.viewmodel

import dev.draftine.advices.domain.interactor.AdviceInteractor
import dev.draftine.advices.presentation.model.AdviceModel
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.imagetape.domain.interactor.ImageTapeInteractor
import dev.draftine.imagetape.presentation.model.ImageTapeModel
import dev.draftine.main.presentation.model.MainViewState
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.main.presentation.viewmodel.mapper.AdviceMapper
import dev.draftine.main.presentation.viewmodel.mapper.ExchangeRateMapper
import dev.draftine.main.presentation.viewmodel.mapper.ImageTapeMapper
import dev.draftine.rates.domain.interactor.ExchangeRateInteractor
import dev.draftine.rates.presentation.model.ExchangeRateCardModel
import dev.draftine.ui.image.Image
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val exchangeRateInteractor: ExchangeRateInteractor,
    private val adviceInteractor: AdviceInteractor,
    private val imageTapeInteractor: ImageTapeInteractor,
    private val exchangeRateMapper: ExchangeRateMapper,
    private val adviceMapper: AdviceMapper,
    private val imageTapeMapper: ImageTapeMapper,
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
            val imageTapeModel = loadImageTape()

            contentState.emit(
                MainViewState.Data(
                    list = listOf(
                        usdExchangeRateModel,
                        adviceModel,
                        imageTapeModel
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

    private suspend fun loadImageTape(): ImageTapeModel {
        return imageTapeInteractor.loadImageTape()
            .run { imageTapeMapper.map(this) }
    }

    fun openImageViewer(tapeImage: Image) {
        mainNavigator.openTapeImageInViewer(tapeImage)
    }
}