package dev.draftine.feed.presentation.viewmodel.provider

import android.graphics.drawable.Drawable
import dev.draftine.feed.R
import dev.draftine.utils.resources.ResourcesProvider

class FeedResourcesProvider(private val resourcesProvider: ResourcesProvider) {

    val cameraTitle = resourcesProvider.getString(R.string.feed_camera_list_item_title)

    val cameraIcon: Drawable
        get() = requireNotNull(resourcesProvider.getDrawable(R.drawable.ic_feature_camera))
}