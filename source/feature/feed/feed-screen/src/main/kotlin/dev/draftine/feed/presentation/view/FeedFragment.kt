package dev.draftine.feed.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.feed.R
import dev.draftine.feed.presentation.model.FeedScreenState
import dev.draftine.feed.presentation.view.adapter.FeedListRecyclerItem
import dev.draftine.feed.presentation.viewmodel.FeedViewModel
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.container.PullToRefreshContainer
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.list.RecyclerView
import dev.draftine.ui.recycler.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment :
    BaseFragment<FeedViewModel>(R.layout.feed_fragment),
    BottomNavigationFragment {

    override val viewModel: FeedViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.feed_toolbar)
    private val pullToRefresh: PullToRefreshContainer by findView(R.id.feed_pull_to_refresh)
    private val feedList: RecyclerView by findView(R.id.feed_list)
    private var feedListAdapter: RecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefresh.isEnabled = false
        feedListAdapter = createFeedListAdapter()
        viewModel.apply {
            screenState.observeOnCreated(lifecycleScope) {
                renderState(it)
            }
        }
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }

    private fun renderState(state: FeedScreenState) {
        when (state) {
            is FeedScreenState.Content -> showContent(state)
            is FeedScreenState.Loading -> Unit
        }
    }

    private fun showContent(content: FeedScreenState.Content) {
        feedListAdapter?.submitList(content.list)
    }

    private fun createFeedListAdapter(): RecyclerAdapter {
        return RecyclerAdapter.create(
            feedList,
            listOf(
                FeedListRecyclerItem { viewModel.onFeedListItemClick(it) }
            )
        )
    }
}