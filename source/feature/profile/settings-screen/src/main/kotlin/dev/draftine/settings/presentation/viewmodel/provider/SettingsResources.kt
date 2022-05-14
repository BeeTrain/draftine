package dev.draftine.settings.presentation.viewmodel.provider

import android.graphics.drawable.Drawable
import dev.draftine.settings.R
import dev.draftine.utils.resources.ResourcesProvider

class SettingsResources(private val resourcesProvider: ResourcesProvider) {

    val appThemeItemTitle = resourcesProvider.getString(R.string.settings_screen_app_theme_item_title)

    val aboutAppItemTitle = resourcesProvider.getString(R.string.settings_screen_about_app_item_title)

    val chevronRight: Drawable
        get() = requireNotNull(resourcesProvider.getDrawable(R.drawable.ic_chevron_right, R.attr.colorOnBackground))
}