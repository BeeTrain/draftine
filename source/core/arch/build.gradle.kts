import AndroidDependencies.navigation
import AndroidDependencies.ui
import DIDependencies.koin

plugins {
    id(Plugins.androidModule)
    id(Plugins.kotlinKapt)
}

dependencies {
    ui()
    koin()
    navigation()
}