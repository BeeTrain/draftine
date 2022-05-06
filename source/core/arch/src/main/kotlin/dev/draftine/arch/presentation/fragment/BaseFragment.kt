package dev.draftine.arch.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dev.draftine.arch.extension.OnSystemInsetsChangedListener
import dev.draftine.arch.extension.observeOnCreated
import dev.draftine.arch.presentation.observer.LoadingObserver
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

abstract class BaseFragment<T : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId),
    OnSystemInsetsChangedListener,
    KoinScopeComponent {

    override val scope: Scope by lazy(LazyThreadSafetyMode.NONE) { createScope(this) }

    abstract val viewModel: T?

    private var runOnResume: Runnable? = null

    private var isAfterOnSavedState: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isAfterOnSavedState = false
        viewModel?.let {
            setupErrorHandling(it.error)
            setupLoading(it.loading)
        }
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        runOnResume?.run()
        runOnResume = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isAfterOnSavedState = true
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) = Unit

    fun postOnResume(run: Runnable) {
        if (isAfterOnSavedState) {
            runOnResume = run
        } else {
            run.run()
        }
    }

    protected open fun createLoadingObserver(): Observer<Boolean?> = LoadingObserver(null)

    protected open fun createErrorObserver(): Observer<Throwable?>? = null

    private fun setupErrorHandling(errorState: StateFlow<Throwable?>) {
        createErrorObserver()?.let { observer ->
            errorState.observeOnCreated(lifecycleScope) { error ->
                observer.onChanged(error)
            }
        }
    }

    private fun setupLoading(loadingState: StateFlow<Boolean>) {
        createLoadingObserver().let { observer ->
            loadingState.observeOnCreated(lifecycleScope) {
                observer.onChanged(it)
            }
        }
    }
}