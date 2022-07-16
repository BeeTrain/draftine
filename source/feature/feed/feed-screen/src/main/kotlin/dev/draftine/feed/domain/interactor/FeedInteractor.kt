package dev.draftine.feed.domain.interactor

import dev.draftine.feed.domain.model.FeedFeature

class FeedInteractor {

    fun loadFeedFeatures(): List<FeedFeature> {
        return FeedFeature.values().toList()
    }
}