package dev.draftine.utils.theme

import androidx.appcompat.app.AppCompatDelegate
import dev.draftine.utils.theme.api.ThemeService
import dev.draftine.utils.theme.api.ThemeStorage
import dev.draftine.utils.theme.api.model.AppTheme
import dev.draftine.utils.theme.mapper.AppThemeMapper

class AppThemeApplier(
    private val appThemeStorage: ThemeStorage,
    private val appThemeMapper: AppThemeMapper
) : ThemeService {

    init {
        val currentAppTheme = appThemeStorage.getAppTheme()
        applyNightModeByTheme(currentAppTheme)
    }

    override fun applyAppTheme(appTheme: AppTheme) {
        appThemeStorage.saveAppTheme(appTheme)
        applyNightModeByTheme(appTheme)
    }

    override fun getCurrentAppTheme(): AppTheme {
        return appThemeStorage.getAppTheme()
    }

    private fun applyNightModeByTheme(appTheme: AppTheme) {
        val appCompatTheme = appThemeMapper.mapToAppCompatTheme(appTheme)
        AppCompatDelegate.setDefaultNightMode(appCompatTheme)
    }
}