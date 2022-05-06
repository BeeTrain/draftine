package dev.draftine.app.presentation.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.draftine.app.R
import dev.draftine.app.presentation.viewmodel.ApplicationViewModel
import dev.draftine.arch.presentation.activity.BaseActivity
import dev.draftine.arch.presentation.navigation.BottomNavBarHost
import dev.draftine.navigation.navigator.AppNavigator
import dev.draftine.navigation.presentation.BottomNavBarVisibilityManager
import dev.draftine.ui.container.constraint.ConstraintContainer
import dev.draftine.ui.extension.OnSystemInsetsChangedListener
import dev.draftine.ui.extension.bind
import dev.draftine.ui.extension.setupWindowInsets
import dev.draftine.ui.extension.updateMargin
import dev.draftine.ui.navigation.BottomNavBar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplicationActivity :
    BaseActivity<ApplicationViewModel>(R.layout.application_activity),
    OnSystemInsetsChangedListener,
    BottomNavBarHost {

    private val viewModel: ApplicationViewModel by viewModel()
    private val navigator: AppNavigator by inject()
    private val bottomNavBarVisibilityManager: BottomNavBarVisibilityManager by inject()

    private val rootContainer by bind<ConstraintContainer>(R.id.application_container)
    private val bottomNavigationView by bind<BottomNavBar>(R.id.application_bottom_nav_bar)

    private val navController: NavController
        get() = findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Draftine)
        super.onCreate(savedInstanceState)
        setupWindowInsets(this)

        bottomNavBarVisibilityManager attachTo this
        setupBottomNavigation()
    }

    override fun onDestroy() {
        bottomNavBarVisibilityManager.detach()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        navigator.bindAppController(navController)
    }

    override fun onStop() {
        super.onStop()
        navigator.unbindAppController()
    }

    override fun onApplySystemInsets(insetTop: Int, insetBottom: Int) {
        bottomNavigationView.updateMargin(bottom = insetBottom)
        supportFragmentManager.fragments
            .filterIsInstance<OnSystemInsetsChangedListener>()
            .forEach { it.onApplySystemInsets(insetTop, insetBottom) }
    }

    override fun setBottomNavigationVisible(isVisible: Boolean) {
        bottomNavigationView.isVisible = isVisible
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun findNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.application_nav_host) as NavHostFragment
        return navHostFragment.navController
    }
}