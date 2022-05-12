package dev.draftine.settings.presentation.viewmodel.mapper

import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.presentation.model.SettingsItem
import dev.draftine.settings.presentation.model.SettingsToggleItem
import dev.draftine.settings.presentation.viewmodel.provider.SettingsResources

class SettingsMapper(private val settingsResources: SettingsResources) {

    fun map(settings: List<Setting>): List<SettingsItem> {
        return settings.map { setting ->
            when (setting) {
                is Setting.Theme -> SettingsToggleItem(
                    settingsResources.appThemeItemTitle,
                    setting.isNightTheme,
                    setting
                )
            }
        }
    }
}