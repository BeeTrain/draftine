import AndroidDependencies.ui
import DIDependencies.koin

plugins {
    id(Plugins.androidModule)
    id(Plugins.kotlinKapt)
}

dependencies {
    implementation(projects.arch)
    implementation(projects.data)
    koin()
    ui()
}