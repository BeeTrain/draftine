package dev.draftine.settings.presentation.view

import android.os.Bundle
import android.view.View
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.settings.R
import dev.draftine.settings.presentation.viewmodel.SettingsViewModel
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.extension.startAnimation
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.image.ImageView
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsViewModel>(R.layout.settings_fragment) {

    override val viewModel: SettingsViewModel by viewModel()

    private val closeIcon: ImageView by findView(R.id.settings_close)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeIcon.setOnClickWithDelayListener {
            it.startAnimation(R.anim.rotation)
            viewModel.closeSettings()
        }
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop, bottom = insetBottom)
    }
}