import extension.prepareReleaseNotes

plugins {
    id(Plugins.applicationModule)
    id(Plugins.googleServices)
    id(Plugins.firebaseAppDistribution)
}

android {

    buildTypes {

        BuildTypes.DEBUG.apply {
            getByName(title) {
                firebaseAppDistribution {
                    appId = System.getenv(AppConfig.KEY_APP_ID)
                    groups = AppConfig.testingGroup
                    releaseNotes = prepareReleaseNotes()
                }
            }
        }
    }
}