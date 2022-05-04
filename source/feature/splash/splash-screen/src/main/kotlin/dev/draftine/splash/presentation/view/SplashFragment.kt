package dev.draftine.splash.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.splash.R
import dev.draftine.splash.presentation.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashViewModel>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()
}