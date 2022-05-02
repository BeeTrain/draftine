package dev.draftine.navigation.app.navigator

import androidx.navigation.NavController

class ApplicationNavigator: AppNavigator {

    private var appNavController: NavController? = null


    override fun bindAppController(navController: NavController) {
        appNavController = navController
    }

    override fun unbindAppController() {
        appNavController = null
    }
}