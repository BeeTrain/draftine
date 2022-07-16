package dev.draftine.feed.presentation.viewmodel.mapper

import dev.draftine.feed.domain.model.FeedFeature
import dev.draftine.feed.presentation.model.FeedListItem
import dev.draftine.feed.presentation.viewmodel.factory.FeedListItemFactory

class FeedListMapper(private val feedListItemFactory: FeedListItemFactory) {

    fun map(feedFeatures: List<FeedFeature>): List<FeedListItem> {
        return feedFeatures.map { feedFeature ->
            when (feedFeature) {
                FeedFeature.CAMERA -> feedListItemFactory.createCameraListItem(feedFeature)
            }
        }
    }
}