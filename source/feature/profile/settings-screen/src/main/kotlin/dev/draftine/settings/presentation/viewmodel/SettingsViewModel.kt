package dev.draftine.settings.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.settings.domain.interactor.SettingsInteractor
import dev.draftine.settings.domain.model.Setting
import dev.draftine.settings.presentation.model.SettingsListItem
import dev.draftine.settings.presentation.model.SettingsToggleItem
import dev.draftine.settings.presentation.navigation.SettingsNavigator
import dev.draftine.settings.presentation.viewmodel.mapper.SettingsMapper
import kotlinx.coroutines.flow.map

class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor,
    private val settingsNavigator: SettingsNavigator,
    private val settingsMapper: SettingsMapper
) : BaseViewModel() {

    val settingsState = settingsInteractor.loadSettings().map {
        settingsMapper.map(it)
    }

    fun onSettingToggled(item: SettingsToggleItem, isChecked: Boolean) {
        when (item.payload) {
            is Setting.Theme -> settingsInteractor.updateAppTheme(isChecked.not())
            else -> Unit
        }
    }

    fun onSettingClicked(item: SettingsListItem) {
        when (item.payload) {
            is Setting.AboutApp -> settingsNavigator.openAboutApp()
            else -> Unit
        }
    }

    fun closeSettings() {
        settingsNavigator.closeSettings()
    }
}