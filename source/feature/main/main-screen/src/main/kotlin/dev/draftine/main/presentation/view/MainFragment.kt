package dev.draftine.main.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.main.R
import dev.draftine.main.presentation.viewmodel.MainViewModel
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.updateMargin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainViewModel>(R.layout.main_fragment),
    BottomNavigationFragment {

    override val viewModel: MainViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.main_toolbar)

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }
}