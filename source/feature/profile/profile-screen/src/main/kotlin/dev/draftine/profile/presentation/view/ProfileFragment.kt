package dev.draftine.profile.presentation.view

import android.os.Bundle
import android.view.View
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.profile.R
import dev.draftine.profile.presentation.viewmodel.ProfileViewModel
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.extension.startAnimation
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.image.ImageView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileViewModel>(R.layout.profile_fragment),
    BottomNavigationFragment {

    override val viewModel: ProfileViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.profile_toolbar)
    private val settingsIcon: ImageView by findView(R.id.profile_settings)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsIcon.setOnClickWithDelayListener {
            it.startAnimation(R.anim.rotation)
            viewModel.onSettingsClick()
        }
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }
}