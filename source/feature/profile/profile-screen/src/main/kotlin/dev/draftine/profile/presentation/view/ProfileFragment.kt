package dev.draftine.profile.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.profile.R
import dev.draftine.profile.presentation.viewmodel.ProfileViewModel
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.updateMargin
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileViewModel>(R.layout.profile_fragment),
    BottomNavigationFragment {

    override val viewModel: ProfileViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.profile_toolbar)

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }
}