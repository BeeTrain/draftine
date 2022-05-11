package dev.draftine.navigation.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import dev.draftine.arch.presentation.fragment.BottomNavigationFragment
import dev.draftine.arch.presentation.navigation.BottomNavBarHost
import dev.draftine.ui.extension.unsafeLazy
import java.lang.ref.WeakReference

class BottomNavBarVisibilityManager {

    private var activityRef: WeakReference<AppCompatActivity?>? = null

    private val lifecycleCallbacks by unsafeLazy { createLifecycleCallbacks() }

    private var backStackChangedListener: FragmentManager.OnBackStackChangedListener? = null

    infix fun attachTo(activity: AppCompatActivity) {
        activity.apply {
            val fragmentManager = findFragmentManager()
            fragmentManager.registerFragmentLifecycleCallbacks(lifecycleCallbacks, false)
            createBackStackChangedListener(fragmentManager).also { listener ->
                fragmentManager.addOnBackStackChangedListener(listener)
                backStackChangedListener = listener
            }
            activityRef = WeakReference(activity)
        }
    }

    fun detach() {
        activityRef?.get()
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(lifecycleCallbacks)
        backStackChangedListener = null
        activityRef = null
    }

    private fun FragmentManager.onFragmentStackChanged() {
        val currentFragment = fragments.last()
        if (currentFragment !is NavHostFragment && currentFragment !is DialogFragment) {
            val isNavBarVisible = currentFragment is BottomNavigationFragment
            activityRef?.get()?.apply {
                (this as? BottomNavBarHost)?.setBottomNavigationVisible(isNavBarVisible)
            }
        }
    }

    private fun AppCompatActivity.findFragmentManager(): FragmentManager {
        val fragmentManager = supportFragmentManager.fragments
            .find { it is NavHostFragment }
            ?.childFragmentManager

        return requireNotNull(fragmentManager)
    }

    private fun createLifecycleCallbacks(): FragmentManager.FragmentLifecycleCallbacks {
        return object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentCreated(
                fragmentManager: FragmentManager,
                fragment: Fragment,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentCreated(fragmentManager, fragment, savedInstanceState)
                if (fragment is NavHostFragment) {
                    fragment.childFragmentManager.registerFragmentLifecycleCallbacks(this, false)
                }
            }

            override fun onFragmentDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment)
                if (fragment is NavHostFragment) {
                    fragment.childFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                }
            }

            override fun onFragmentViewCreated(
                fragmentManager: FragmentManager,
                fragment: Fragment,
                view: View,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentViewCreated(fragmentManager, fragment, view, savedInstanceState)
                fragmentManager.onFragmentStackChanged()
            }
        }
    }

    private fun createBackStackChangedListener(
        fragmentManager: FragmentManager
    ): FragmentManager.OnBackStackChangedListener {
        return FragmentManager.OnBackStackChangedListener { fragmentManager.onFragmentStackChanged() }
    }
}