import AndroidDependencies.navigation
import DIDependencies.koin
import extension.applyScreenModules

plugins {
    id(Plugins.androidModule)
}

dependencies {
    applyScreenModules()
    navigation()
    koin()
}