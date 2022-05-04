package dev.draftine.arch.presentation.activity

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.draftine.arch.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity {

    private constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
}