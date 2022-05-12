package dev.draftine.utils.theme.api

import dev.draftine.utils.theme.api.model.AppTheme

interface ThemeStorage {

    fun getAppTheme(): AppTheme

    fun saveAppTheme(appTheme: AppTheme)
}