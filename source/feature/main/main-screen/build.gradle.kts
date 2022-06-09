import MiscDependencies.coil
import MiscDependencies.jodaTime

plugins {
    id(Plugins.featureModule)
}

dependencies {
    implementation(projects.exchangeRates)
    implementation(projects.imageTape)
    implementation(projects.advices)
    implementation(projects.utils)

    coil()
    jodaTime()
}