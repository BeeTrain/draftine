package dev.draftine.settings.domain.model

sealed interface Setting {

    data class Theme(val isNightTheme: Boolean) : Setting

    object AboutApp : Setting
}