import MiscDependencies.applyJodaTime

plugins {
    id(Plugins.featureModule)
}

dependencies {
    implementation(projects.ui)
    implementation(projects.network)

    applyJodaTime()
}