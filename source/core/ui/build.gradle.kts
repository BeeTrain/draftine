import AndroidDependencies.navigation
import AndroidDependencies.ui
import MiscDependencies.lottie
import MiscDependencies.coil

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