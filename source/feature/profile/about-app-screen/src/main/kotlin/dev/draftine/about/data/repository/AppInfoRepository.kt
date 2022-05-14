package dev.draftine.about.data.repository

import dev.draftine.about.domain.model.AppInfo
import dev.draftine.about.domain.repository.AboutAppInfoRepository
import dev.draftine.utils.info.SystemInfoManager

class AppInfoRepository(private val systemInfoManager: SystemInfoManager): AboutAppInfoRepository {

    override fun getAppInfo(): List<AppInfo> {
        return listOf(
            AppInfo.AppVersion(systemInfoManager.appVersionName),
            AppInfo.DeviceName(systemInfoManager.device),
            AppInfo.SystemVersion(systemInfoManager.systemVersion),
            AppInfo.SdkVersion(systemInfoManager.systemSdkVersion.toString()),
        )
    }
}