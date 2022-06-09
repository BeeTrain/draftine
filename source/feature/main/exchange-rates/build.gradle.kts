import MiscDependencies.jodaTime

plugins {
    id(Plugins.featureModule)
}

dependencies {
    implementation(projects.ui)
    implementation(projects.network)

    jodaTime()
}