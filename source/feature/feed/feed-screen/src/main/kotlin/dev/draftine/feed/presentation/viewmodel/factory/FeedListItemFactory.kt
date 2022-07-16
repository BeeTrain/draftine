package dev.draftine.feed.presentation.viewmodel.factory

import dev.draftine.feed.domain.model.FeedFeature
import dev.draftine.feed.presentation.model.FeedListItem
import dev.draftine.feed.presentation.viewmodel.provider.FeedResourcesProvider

class FeedListItemFactory(private val resourcesProvider: FeedResourcesProvider) {

    fun createCameraListItem(feedFeature: FeedFeature): FeedListItem {
        return FeedListItem(
            name = resourcesProvider.cameraTitle,
            startIcon = resourcesProvider.cameraIcon,
            payload = feedFeature
        )
    }
}