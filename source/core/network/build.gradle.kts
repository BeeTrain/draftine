import DIDependencies.koin
import NetworkDependencies.network

plugins {
    id(Plugins.androidModule)
    id(Plugins.kotlinKapt)
}

dependencies {
    koin()
    network()
}