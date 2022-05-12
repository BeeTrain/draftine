package dev.draftine.utils.theme.api

import dev.draftine.utils.theme.api.model.AppTheme

interface ThemeService {

    fun applyAppTheme(appTheme: AppTheme)

    fun getCurrentAppTheme(): AppTheme
}