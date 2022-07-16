package dev.draftine.main.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.main.domain.interactor.MainInteractor
import dev.draftine.main.presentation.model.MainScreenState
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.main.presentation.viewmodel.mapper.MainMapper
import dev.draftine.ui.image.Image
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val mainInteractor: MainInteractor,
    private val mainMapper: MainMapper,
    private val mainNavigator: MainNavigator
) : BaseViewModel() {

    val screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)

    fun openExchangeRateUrl(url: String) {
        mainNavigator.openExchangeRateUrl(url)
    }

    fun loadData() {
        launchLoadingErrorJob {
            val data = mainInteractor.loadData()
                .mapNotNull { mainMapper.map(it) }

            screenState.emit(
                MainScreenState.Content(
                    list = data
                )
            )
        }
    }

    fun openImageViewer(tapeImage: Image) {
        mainNavigator.openTapeImageInViewer(tapeImage)
    }
}