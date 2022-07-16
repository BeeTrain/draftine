import AndroidDependencies.navigation
import AndroidDependencies.ui
import MiscDependencies.coil
import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(projects.icons)

    ui()
    coil()
    lottie()
    navigation()
}