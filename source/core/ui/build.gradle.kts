import AndroidDependencies.navigation
import AndroidDependencies.ui
import MiscDependencies.lottie

plugins {
    id(Plugins.androidModule)
}

dependencies {
    implementation(projects.icons)

    ui()
    lottie()
    navigation()
}