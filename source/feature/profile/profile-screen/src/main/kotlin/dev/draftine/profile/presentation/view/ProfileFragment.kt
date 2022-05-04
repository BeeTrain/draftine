package dev.draftine.profile.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.profile.R
import dev.draftine.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileViewModel>(R.layout.profile_fragment),
    BottomNavigationFragment {

    override val viewModel: ProfileViewModel by viewModel()
}