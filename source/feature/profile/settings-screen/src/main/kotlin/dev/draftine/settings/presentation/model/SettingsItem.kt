package dev.draftine.settings.presentation.model

import dev.draftine.settings.domain.model.Setting
import dev.draftine.ui.recycler.Item

sealed class SettingsItem : Item()

data class SettingsToggleItem(
    val name: String,
    val isEnabled: Boolean,
    override val payload: Setting
) : SettingsItem()
