package dev.draftine.arch.presentation.activity

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity {
    private constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)
}