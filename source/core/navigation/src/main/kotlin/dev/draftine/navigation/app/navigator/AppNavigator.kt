package dev.draftine.navigation.app.navigator

import androidx.navigation.NavController

interface AppNavigator {

    fun bindAppController(navController: NavController)

    fun unbindAppController()
}