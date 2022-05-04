import AndroidDependencies.navigation
import DIDependencies.koin
import extension.applyScreenModules

plugins {
    id(Plugins.androidModule)
    id(Plugins.kotlinKapt)
    id(Plugins.navigation)
}

dependencies {
    implementation(projects.arch)
    implementation(projects.ui)
    applyScreenModules()
    navigation()
    koin()
}