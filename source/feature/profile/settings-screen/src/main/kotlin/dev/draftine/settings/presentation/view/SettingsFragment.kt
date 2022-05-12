package dev.draftine.settings.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.settings.R
import dev.draftine.settings.presentation.model.SettingsToggleItem
import dev.draftine.settings.presentation.view.adapter.SettingsToggleRecyclerItem
import dev.draftine.settings.presentation.viewmodel.SettingsViewModel
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.extension.startAnimation
import dev.draftine.ui.extension.unsafeLazy
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.image.ImageView
import dev.draftine.ui.list.RecyclerView
import dev.draftine.ui.recycler.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsViewModel>(R.layout.settings_fragment) {

    override val viewModel: SettingsViewModel by viewModel()

    private val closeIcon: ImageView by findView(R.id.settings_close)
    private val settingsList: RecyclerView by findView(R.id.settings_list)
    private val settingsAdapter by unsafeLazy { createSettingsAdapter() }

    private var onToggleSettingClick: ((SettingsToggleItem, Boolean) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onToggleSettingClick = { item, isChecked ->
            viewModel.onSettingToggled(item, isChecked)
        }
        viewModel.settingsState.observeOnCreated(lifecycleScope) {
            settingsAdapter.submitList(it)
        }
        closeIcon.setOnClickWithDelayListener {
            it.startAnimation(R.anim.rotation)
            viewModel.closeSettings()
        }
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop, bottom = insetBottom)
    }

    private fun createSettingsAdapter(): RecyclerAdapter {
        return RecyclerAdapter.create(
            settingsList,
            listOf(SettingsToggleRecyclerItem(onToggleSettingClick))
        )
    }
}