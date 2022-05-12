package dev.draftine.utils.theme.repository

import dev.draftine.data.storage.SimpleDataStorage
import dev.draftine.utils.theme.api.ThemeStorage
import dev.draftine.utils.theme.api.model.AppTheme
import dev.draftine.utils.theme.mapper.AppThemeMapper

private const val KEY_APP_THEME = "APP_THEME"

class AppThemeStorage(
    private val simpleDataStorage: SimpleDataStorage,
    private val appThemeMapper: AppThemeMapper
) : ThemeStorage {

    override fun getAppTheme(): AppTheme {
        val preferencesAppThemeValue = simpleDataStorage.getNullableInt(KEY_APP_THEME)

        return appThemeMapper.mapToAppTheme(preferencesAppThemeValue)
    }

    override fun saveAppTheme(appTheme: AppTheme) {
        val appCompatTheme = appThemeMapper.mapToAppCompatTheme(appTheme)
        simpleDataStorage.putInt(KEY_APP_THEME, appCompatTheme)
    }
}