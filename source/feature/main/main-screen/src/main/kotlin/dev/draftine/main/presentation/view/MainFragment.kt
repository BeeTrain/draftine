package dev.draftine.main.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.main.R
import dev.draftine.main.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.main_fragment) {

    override val viewModel: MainViewModel by viewModel()
}