package dev.draftine.settings.presentation.viewmodel.mapper

import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.presentation.model.SettingsItem
import dev.draftine.settings.presentation.viewmodel.factory.SettingsItemFactory

class SettingsMapper(private val settingsItemFactory: SettingsItemFactory) {

    fun map(settings: List<Setting>): List<SettingsItem> {
        return settings.map { setting -> settingsItemFactory.create(setting) }
    }
}