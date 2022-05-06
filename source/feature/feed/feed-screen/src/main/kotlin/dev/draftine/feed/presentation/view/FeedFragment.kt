package dev.draftine.feed.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.feed.R
import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.updateMargin
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment :
    BaseFragment<FeedViewModel>(R.layout.feed_fragment),
    BottomNavigationFragment {

    override val viewModel: FeedViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.feed_toolbar)

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }
}