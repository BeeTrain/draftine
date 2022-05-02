package dev.draftine.app.presentation.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

class ApplicationFragment : NavHostFragment() {

    private var destinationChangedListener = createDestinationChangeListener()

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(destinationChangedListener)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(destinationChangedListener)
    }

    private fun createDestinationChangeListener(): NavController.OnDestinationChangedListener {
        return NavController.OnDestinationChangedListener { _, _, _ ->
            view?.let { Navigation.setViewNavController(it, navController) }
        }
    }
}