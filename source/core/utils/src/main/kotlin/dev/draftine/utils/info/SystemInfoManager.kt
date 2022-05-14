package dev.draftine.utils.info

import android.content.Context
import android.os.Build
import dev.draftine.utils.extension.getPackageInfo

class SystemInfoManager(private val context: Context) {

    val appVersionName = getVersionName()
    val systemVersion = Build.VERSION.RELEASE.orEmpty()
    val systemSdkVersion = Build.VERSION.SDK_INT
    val device = Build.DEVICE.orEmpty()

    private fun getVersionName(): String {
        return context.getPackageInfo(context.packageName)
            ?.versionName
            .orEmpty()
    }
}