package dev.draftine.navigation.navigator

import androidx.navigation.NavController

interface AppNavigator {

    fun bindAppController(navController: NavController)

    fun unbindAppController()
}