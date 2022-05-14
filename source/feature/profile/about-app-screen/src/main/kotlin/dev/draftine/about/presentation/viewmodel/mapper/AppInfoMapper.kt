package dev.draftine.about.presentation.viewmodel.mapper

import dev.draftine.about.domain.model.AppInfo
import dev.draftine.about.presentation.model.AppInfoModel
import dev.draftine.about.presentation.viewmodel.factory.AppInfoModelFactory

class AppInfoMapper(private val appInfoModelFactory: AppInfoModelFactory) {

    fun map(appInfo: List<AppInfo>): List<AppInfoModel> {
        return appInfo.filter { it.value.isNotEmpty() }
            .map { appInfoModelFactory.create(it) }
    }
}