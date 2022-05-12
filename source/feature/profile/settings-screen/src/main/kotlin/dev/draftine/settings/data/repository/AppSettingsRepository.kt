package dev.draftine.settings.data.repository

import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.domain.repository.SettingsRepository
import dev.draftine.utils.theme.api.ThemeService
import dev.draftine.utils.theme.api.model.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppSettingsRepository(
    private val themeService: ThemeService
) : SettingsRepository {

    private val settingsState = MutableStateFlow(prepareSettings())

    override fun getSettingsState() = settingsState.asStateFlow()

    override fun setAppTheme(theme: Setting.Theme) {
        val appTheme = when (theme.isNightTheme) {
            true -> AppTheme.LIGHT
            false -> AppTheme.DARK
        }
        themeService.applyAppTheme(appTheme)
        settingsState.update { prepareSettings() }
    }

    private fun prepareSettings(): List<Setting> {
        return listOf(
            getAppTheme()
        )
    }

    private fun getAppTheme(): Setting.Theme {
        val theme = themeService.getCurrentAppTheme()
        return Setting.Theme(theme == AppTheme.DARK)
    }
}