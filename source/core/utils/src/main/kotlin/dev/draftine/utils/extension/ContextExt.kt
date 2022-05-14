package dev.draftine.utils.extension

import android.content.Context
import android.content.pm.PackageInfo

fun Context.getPackageInfo(packageName: String): PackageInfo? {
    return try {
        packageManager.getPackageInfo(packageName, 0)
    } catch (e: Exception) {
        null
    }
}