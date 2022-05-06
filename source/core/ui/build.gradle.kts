import AndroidDependencies.navigation
import AndroidDependencies.ui
import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    ui()
    lottie()
    navigation()
}