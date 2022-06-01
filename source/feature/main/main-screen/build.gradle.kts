import MiscDependencies.applyJodaTime

plugins {
    id(Plugins.featureModule)
}

dependencies {
    implementation(projects.exchangeRates)
    implementation(projects.utils)

    applyJodaTime()
}