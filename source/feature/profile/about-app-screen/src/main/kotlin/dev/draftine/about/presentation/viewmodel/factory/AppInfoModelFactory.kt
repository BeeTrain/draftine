package dev.draftine.about.presentation.viewmodel.factory

import dev.draftine.about.domain.model.AppInfo
import dev.draftine.about.presentation.model.AppInfoModel
import dev.draftine.about.presentation.viewmodel.provider.AboutAppResources

class AppInfoModelFactory(
    private val aboutAppResources: AboutAppResources
) {

    fun create(appInfoModel: AppInfo): AppInfoModel {
        return AppInfoModel(
            title = prepareTitle(appInfoModel),
            value = appInfoModel.value
        )
    }

    private fun prepareTitle(appInfoModel: AppInfo): String {
        return when (appInfoModel) {
            is AppInfo.AppVersion -> aboutAppResources.appVersionTitle
            is AppInfo.DeviceName -> aboutAppResources.deviceNameTitle
            is AppInfo.SdkVersion -> aboutAppResources.systemSdkVersionTitle
            is AppInfo.SystemVersion -> aboutAppResources.systemVersionTitle
        }
    }
}