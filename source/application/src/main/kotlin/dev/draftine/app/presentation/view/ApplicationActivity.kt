package dev.draftine.app.presentation.view

import android.os.Bundle
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.draftine.app.R
import dev.draftine.app.presentation.viewmodel.ApplicationViewModel
import dev.draftine.arch.presentation.activity.BaseActivity
import dev.draftine.navigation.app.navigator.AppNavigator
import dev.draftine.ui.container.constraint.ConstraintContainer
import dev.draftine.ui.extension.bind
import dev.draftine.ui.extension.doOnApplyWindowInsets
import dev.draftine.ui.extension.pxToDp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplicationActivity : BaseActivity(R.layout.application_activity) {
    private val viewModel: ApplicationViewModel by viewModel()
    private val navigator: AppNavigator by inject()

    private lateinit var navController: NavController

    private val rootContainer by bind<ConstraintContainer>(R.id.application_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Draftine)
        super.onCreate(savedInstanceState)

        rootContainer.doOnApplyWindowInsets { view, insets, _ ->
            val topInset = insets.systemWindowInsetTop
            val bottomInset = insets.systemWindowInsetBottom

            view.updatePadding(top = topInset, bottom = bottomInset)

            viewModel.updateTopInset(topInset.pxToDp())
            viewModel.updateBottomInset(bottomInset.pxToDp())
            insets.consumeSystemWindowInsets()
        }

        navController = findNavController(R.id.application_nav_host)
    }

    override fun onStart() {
        super.onStart()
        navigator.bindAppController(navController)
    }

    override fun onStop() {
        super.onStop()
        navigator.unbindAppController()
    }
}