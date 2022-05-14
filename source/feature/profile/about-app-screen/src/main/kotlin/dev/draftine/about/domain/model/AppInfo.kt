package dev.draftine.about.domain.model

sealed class AppInfo(open val value: String) {

    data class AppVersion(override val value: String) : AppInfo(value)

    data class SystemVersion(override val value: String) : AppInfo(value)

    data class SdkVersion(override val value: String) : AppInfo(value)

    data class DeviceName(override val value: String) : AppInfo(value)
}