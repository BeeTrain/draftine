package dev.draftine.feed.presentation.view

import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.feed.R
import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment :
    BaseFragment<FeedViewModel>(R.layout.feed_fragment),
    BottomNavigationFragment {

    override val viewModel: FeedViewModel by viewModel()
}