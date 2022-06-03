import MiscDependencies.applyJodaTime

plugins {
    id(Plugins.featureModule)
}

dependencies {
    implementation(projects.exchangeRates)
    implementation(projects.advices)
    implementation(projects.utils)

    applyJodaTime()
}