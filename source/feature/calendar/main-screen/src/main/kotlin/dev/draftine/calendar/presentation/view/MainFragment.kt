package dev.draftine.calendar.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.calendar.R
import dev.draftine.calendar.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainViewModel>(R.layout.main_fragment),
    BottomNavigationFragment {

    override val viewModel: MainViewModel by viewModel()
}