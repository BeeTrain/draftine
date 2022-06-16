package dev.draftine.about.presentation.viewmodel.provider

import dev.draftine.about.R
import dev.draftine.utils.resources.ResourcesProvider

class AboutAppResources(resourcesProvider: ResourcesProvider) {

    val appVersionTitle = resourcesProvider.getString(R.string.about_app_application_version_title)

    val deviceNameTitle = resourcesProvider.getString(R.string.about_app_device_name_title)

    val systemVersionTitle = resourcesProvider.getString(R.string.about_app_system_version_title)

    val deviceIdTitle = resourcesProvider.getString(R.string.about_app_device_id_title)

    val isEmulatorTitle = resourcesProvider.getString(R.string.about_app_is_emulator_title)

    val isRootedTitle = resourcesProvider.getString(R.string.about_app_is_rooted_title)

    val resolutionTitle = resourcesProvider.getString(R.string.about_app_resolution_title)
}