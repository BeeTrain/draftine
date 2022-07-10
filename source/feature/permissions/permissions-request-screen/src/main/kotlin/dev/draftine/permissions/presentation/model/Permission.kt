package dev.draftine.permissions.presentation.model

data class Permission(
    val name: String?,
    val granted: Boolean,
    val shouldShowRequestPermissionRationale: Boolean = false
)