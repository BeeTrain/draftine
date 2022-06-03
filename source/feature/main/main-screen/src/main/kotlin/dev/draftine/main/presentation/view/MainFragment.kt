package dev.draftine.main.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import dev.draftine.advices.presentation.adapter.AdviceRecyclerItem
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.main.R
import dev.draftine.main.presentation.model.MainViewState
import dev.draftine.main.presentation.viewmodel.MainViewModel
import dev.draftine.rates.presentation.view.adapter.ExchangeRateRecyclerItem
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.container.PullToRefreshContainer
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.unsafeLazy
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.list.RecyclerView
import dev.draftine.ui.recycler.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<MainViewModel>(R.layout.main_fragment),
    BottomNavigationFragment {

    override val viewModel: MainViewModel by viewModel()

    private val toolbar: Toolbar by findView(R.id.main_toolbar)
    private val pullToRefresh: PullToRefreshContainer by findView(R.id.main_pull_to_refresh)
    private val mainList: RecyclerView by findView(R.id.main_list)
    private val mainAdapter by unsafeLazy { createMainAdapter() }

    private var onExchangeRateLinkClick: ((String) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefresh.setOnRefreshListener { viewModel.loadData() }
        onExchangeRateLinkClick = { viewModel.openExchangeRateUrl(it) }

        viewModel.apply {
            loading.observeOnCreated(lifecycleScope) { isLoading ->
                pullToRefresh.setLoading(isLoading)
            }
            contentState.observeOnCreated(lifecycleScope) { state ->
                renderState(state)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop)
    }

    private fun renderState(state: MainViewState) {
        when (state) {
            is MainViewState.Data -> showData(state)
            is MainViewState.Loading -> Unit
        }
    }

    private fun showData(data: MainViewState.Data) {
        mainAdapter.submitList(data.list)
    }

    private fun createMainAdapter(): RecyclerAdapter {
        return RecyclerAdapter.create(
            mainList,
            listOf(
                ExchangeRateRecyclerItem(onExchangeRateLinkClick),
                AdviceRecyclerItem()
            )
        )
    }
}