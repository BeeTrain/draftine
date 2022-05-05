import AndroidDependencies.navigation
import AndroidDependencies.ui
import DIDependencies.koin

plugins {
    id(Plugins.androidModule)
}

dependencies {
    ui()
    koin()
    navigation()
}