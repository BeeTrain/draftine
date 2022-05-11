package dev.draftine.profile.presentation.viewmodel

import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import dev.draftine.profile.presentation.navigation.ProfileNavigator

class ProfileViewModel(
    private val profileNavigator: ProfileNavigator
) : BaseViewModel() {

    fun onSettingsClick() {
        profileNavigator.fromProfileToSettings()
    }
}