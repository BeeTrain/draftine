package dev.draftine.settings.domain.interactor

import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

class SettingsInteractor(
    private val settingsRepository: SettingsRepository
) {

    fun loadSettings(): StateFlow<List<Setting>> {
        return settingsRepository.getSettingsState()
    }

    fun updateAppTheme(isNightTheme: Boolean) {
        val theme = Setting.Theme(isNightTheme)
        settingsRepository.setAppTheme(theme)
    }
}