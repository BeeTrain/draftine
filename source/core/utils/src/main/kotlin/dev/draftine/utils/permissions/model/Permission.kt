package dev.draftine.utils.permissions.model

data class Permission(
    val name: String?,
    val granted: Boolean,
    val shouldShowRequestPermissionRationale: Boolean = false
)