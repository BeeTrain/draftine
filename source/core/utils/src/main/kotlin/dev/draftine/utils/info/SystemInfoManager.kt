package dev.draftine.utils.info

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import java.io.File
import java.util.UUID

private const val BUILD_TAGS_KEY = "test-keys"
private const val SDK = "sdk"
private const val GOOGLE_SDK = "google_sdk"
private const val APP_VERSION_PATTERN = "%s %s"
private const val DEVICE_NAME_PATTERN = "%s %s"
private const val ANDROID_VERSION_PATTERN = "Android (%s)"

class SystemInfoManager(private val context: Context) {

    fun isRooted(): Boolean {
        return when {
            isEmulator().not() && Build.TAGS != null && Build.TAGS.contains(BUILD_TAGS_KEY) -> true
            File("/system/app/Superuser.apk").exists() -> true
            File("/system/xbin/su").exists() && isEmulator().not() -> true
            else -> false
        }
    }

    fun isEmulator(): Boolean {
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return SDK == Build.PRODUCT || GOOGLE_SDK == Build.PRODUCT || androidId == null
    }

    fun getDeviceId(): String {
        val secureId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val uuid = if (secureId.isNotEmpty()) {
            UUID.nameUUIDFromBytes(secureId.toByteArray())
        } else {
            UUID.randomUUID()
        }
        return uuid.toString()
    }

    fun getAndroidVersion(): String {
        return ANDROID_VERSION_PATTERN.format(Build.VERSION.SDK_INT)
    }

    fun getAndroidVersionNumber(): Int {
        return Build.VERSION.SDK_INT
    }

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else {
            DEVICE_NAME_PATTERN.format(manufacturer, model)
        }
    }

    fun getWidthScreen(): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

    fun getHeightScreen(): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels
    }

    fun getDeviceResolution(): String {
        return "${getWidthScreen()}x${getHeightScreen()}"
    }

    fun getAppVersionName(): String {
        val versionName = getVersionName()
        val versionCode = getAppVersionCode().toString()
        return APP_VERSION_PATTERN.format(versionName, versionCode)
    }

    fun getAppVersionCode(): Int {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            -1
        }
    }

    fun getVersionName(): String {
        return try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            info.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "n/a"
        }
    }
}