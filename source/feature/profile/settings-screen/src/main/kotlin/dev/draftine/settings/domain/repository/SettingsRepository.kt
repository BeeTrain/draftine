package dev.draftine.settings.domain.repository

import dev.draftine.settings.domain.model.Setting
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {

    fun getSettingsState(): StateFlow<List<Setting>>

    fun setAppTheme(theme: Setting.Theme)
}