package dev.draftine.settings.presentation.viewmodel.factory

import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.presentation.model.SettingsItem
import dev.draftine.settings.presentation.model.SettingsListItem
import dev.draftine.settings.presentation.model.SettingsToggleItem
import dev.draftine.settings.presentation.viewmodel.provider.SettingsResources

class SettingsItemFactory(private val settingsResources: SettingsResources) {

    fun create(setting: Setting): SettingsItem {
        return when (setting) {
            is Setting.Theme -> createAppThemeSettingsItem(setting)
            is Setting.AboutApp -> createAboutAppSettingsItem(setting)
        }
    }

    private fun createAppThemeSettingsItem(setting: Setting.Theme): SettingsToggleItem {
        return SettingsToggleItem(
            name = settingsResources.appThemeItemTitle,
            isEnabled = setting.isNightTheme,
            payload = setting
        )
    }

    private fun createAboutAppSettingsItem(setting: Setting.AboutApp): SettingsItem {
        return SettingsListItem(
            name = settingsResources.aboutAppItemTitle,
            endIcon = requireNotNull(settingsResources.chevronRight),
            payload = setting
        )
    }
}