package dev.draftine.settings.presentation.model

import android.graphics.drawable.Drawable
import dev.draftine.settings.domain.model.Setting
import dev.draftine.ui.recycler.Item

sealed class SettingsItem(override val payload: Setting) : Item()

data class SettingsToggleItem(
    val name: String,
    val isEnabled: Boolean,
    override val payload: Setting
) : SettingsItem(payload)

data class SettingsListItem(
    val name: String,
    val endIcon: Drawable,
    override val payload: Setting
) : SettingsItem(payload)