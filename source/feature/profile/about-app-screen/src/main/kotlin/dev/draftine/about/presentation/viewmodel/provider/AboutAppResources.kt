package dev.draftine.about.presentation.viewmodel.provider

import dev.draftine.utils.resources.ResourcesProvider
import dev.draftine.about.R

class AboutAppResources(resourcesProvider: ResourcesProvider) {

    val appVersionTitle = resourcesProvider.getString(R.string.about_app_application_version_title)

    val deviceNameTitle = resourcesProvider.getString(R.string.about_app_device_name_title)

    val systemVersionTitle = resourcesProvider.getString(R.string.about_app_system_version_title)

    val systemSdkVersionTitle = resourcesProvider.getString(R.string.about_app_sdk_version_title)
}