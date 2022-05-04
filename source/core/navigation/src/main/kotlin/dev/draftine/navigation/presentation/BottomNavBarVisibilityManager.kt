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
import java.lang.ref.WeakReference

class BottomNavBarVisibilityManager {

    private var activityRef: WeakReference<AppCompatActivity?>? = null

    private val lifecycleCallbacks by lazy { createLifecycleCallbacks() }

    infix fun attachTo(activity: AppCompatActivity) {
        activity.apply {
            supportFragmentManager.fragments
                .find { it is NavHostFragment }
                ?.childFragmentManager
                ?.registerFragmentLifecycleCallbacks(lifecycleCallbacks, false)
            activityRef = WeakReference(activity)
        }
    }

    fun detach() {
        activityRef?.get()
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(lifecycleCallbacks)
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
                if (fragment !is NavHostFragment && fragment !is DialogFragment) {
                    val isNavBarVisible = fragment is BottomNavigationFragment
                    activityRef?.get()?.apply {
                        (this as? BottomNavBarHost)?.setBottomNavigationVisible(isNavBarVisible)
                    }
                }
            }
        }
    }
}