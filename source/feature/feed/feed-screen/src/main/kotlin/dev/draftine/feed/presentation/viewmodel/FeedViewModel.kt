package dev.draftine.feed.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.feed.domain.interactor.FeedInteractor
import dev.draftine.feed.domain.model.FeedFeature
import dev.draftine.feed.presentation.model.FeedListItem
import dev.draftine.feed.presentation.model.FeedScreenState
import dev.draftine.feed.presentation.navigation.FeedNavigator
import dev.draftine.feed.presentation.viewmodel.mapper.FeedListMapper
import dev.draftine.utils.resources.ResourcesProvider
import kotlinx.coroutines.flow.MutableStateFlow

class FeedViewModel(
    private val feedInteractor: FeedInteractor,
    private val feedListMapper: FeedListMapper,
    private val feedNavigator: FeedNavigator
) : BaseViewModel() {

    val screenState = MutableStateFlow<FeedScreenState>(FeedScreenState.Loading)

    init {
        loadData()
    }

    private fun loadData() {
        launchLoadingErrorJob {
            feedInteractor.loadFeedFeatures()
                .run { feedListMapper.map(this) }
                .also { listItems ->
                    screenState.emit(
                        FeedScreenState.Content(
                            list = listItems
                        )
                    )
                }
        }
    }

    fun onFeedListItemClick(feedListItem: FeedListItem) {
        when (feedListItem.payload) {
            FeedFeature.CAMERA -> feedNavigator.openCamera()
        }
    }
}