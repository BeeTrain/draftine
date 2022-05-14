package dev.draftine.about.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import dev.draftine.about.R
import dev.draftine.about.presentation.view.adapter.AboutAppRecyclerItem
import dev.draftine.about.presentation.viewmodel.AboutAppViewModel
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.fragment.BaseFragment
import dev.draftine.ui.appbar.Toolbar
import dev.draftine.ui.extension.findView
import dev.draftine.ui.extension.unsafeLazy
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.list.RecyclerView
import dev.draftine.ui.recycler.RecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutAppFragment : BaseFragment<AboutAppViewModel>(R.layout.about_app_fragment) {

    override val viewModel: AboutAppViewModel by viewModel()

    private val toolbar by findView<Toolbar>(R.id.about_app_toolbar)
    private val aboutAppList by findView<RecyclerView>(R.id.about_app_list)

    private val aboutAppAdapter by unsafeLazy { createAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        viewModel.appInfoData.observeOnCreated(lifecycleScope) {
            aboutAppAdapter.submitList(it)
        }
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        view?.updateMargin(top = insetTop, bottom = insetBottom)
    }

    private fun createAdapter(): RecyclerAdapter {
        return RecyclerAdapter.create(
            aboutAppList,
            listOf(
                AboutAppRecyclerItem()
            )
        )
    }
}