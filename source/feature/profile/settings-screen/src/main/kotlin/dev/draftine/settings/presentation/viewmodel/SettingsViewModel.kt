package dev.draftine.settings.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.settings.presentation.navigation.SettingsNavigator

class SettingsViewModel(
    private val settingsNavigator: SettingsNavigator
) : BaseViewModel() {

    fun closeSettings() {
        settingsNavigator.closeSettings()
    }
}