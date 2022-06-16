package dev.draftine.about.data.repository

import dev.draftine.about.domain.model.AppInfo
import dev.draftine.about.domain.repository.AboutAppInfoRepository
import dev.draftine.utils.info.SystemInfoManager

class AppInfoRepository(
    private val systemInfoManager: SystemInfoManager
) : AboutAppInfoRepository {

    override fun getAppInfo(): List<AppInfo> {
        return listOf(
            AppInfo.DeviceId(systemInfoManager.getDeviceId()),
            AppInfo.DeviceName(systemInfoManager.getDeviceName()),
            AppInfo.IsRooted(systemInfoManager.isRooted().toString()),
            AppInfo.IsEmulator(systemInfoManager.isEmulator().toString()),
            AppInfo.AppVersion(systemInfoManager.getAppVersionName()),
            AppInfo.SystemVersion(systemInfoManager.getAndroidVersion()),
            AppInfo.Resolution(systemInfoManager.getDeviceResolution()),
        )
    }
}