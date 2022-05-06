package dev.draftine.arch.presentation.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.draftine.arch.extension.OnSystemInsetsChangedListener
import dev.draftine.arch.extension.setupWindowInsets
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

abstract class BaseActivity<T : BaseViewModel> :
    AppCompatActivity,
    OnSystemInsetsChangedListener,
    KoinScopeComponent {

    private constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override val scope: Scope by lazy(LazyThreadSafetyMode.NONE) { createScope(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindowInsets(this)
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) = Unit
}