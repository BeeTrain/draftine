package dev.draftine.main.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.imagetape.domain.model.ImageTapeItem
import dev.draftine.main.domain.interactor.MainInteractor
import dev.draftine.main.presentation.model.MainScreenState
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.main.presentation.viewmodel.mapper.ImageTapeListMapper
import dev.draftine.main.presentation.viewmodel.mapper.MainMapper
import dev.draftine.ui.image.Image
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val mainInteractor: MainInteractor,
    private val mainMapper: MainMapper,
    private val imageTapeListMapper: ImageTapeListMapper,
    private val mainNavigator: MainNavigator
) : BaseViewModel() {

    val screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)

    private var imageTapeItems = emptyList<Image>()

    fun openExchangeRateUrl(url: String) {
        mainNavigator.openExchangeRateUrl(url)
    }

    fun loadData() {
        launchLoadingErrorJob {
            val data = mainInteractor.loadData()
                .also { sourceData -> saveImageTape(sourceData) }
                .mapNotNull { mainMapper.map(it) }

            screenState.emit(
                MainScreenState.Content(
                    list = data
                )
            )
        }
    }

    fun openImageViewer(tapeImage: Image) {
        if (imageTapeItems.isEmpty()) return

        mainNavigator.openTapeImageInViewer(imageTapeItems, tapeImage)
    }

    private fun saveImageTape(data: List<Any>) {
        data.filterIsInstance<List<ImageTapeItem>>()
            .firstOrNull()
            ?.run { imageTapeItems = imageTapeListMapper.map(this) }
    }
}