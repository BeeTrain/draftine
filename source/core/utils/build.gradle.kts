import AndroidDependencies.cameraX
import AndroidDependencies.ui
import DIDependencies.koin

plugins {
    id(Plugins.androidModule)
    id(Plugins.kotlinKapt)
}

dependencies {
    implementation(projects.arch)
    implementation(projects.data)
    implementation(projects.icons)

    cameraX()
    koin()
    ui()
}