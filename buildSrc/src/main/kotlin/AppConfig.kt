import org.gradle.api.JavaVersion

@Suppress("unused")
object AppConfig {
    const val applicationId = "dev.draftine"

    const val compileSdkVersion = 32
    const val minSdkVersion = 26
    const val targetSdkVersion = 32

    val javaVersion = JavaVersion.VERSION_11

    const val testingGroup = "testing-department"

    const val KEY_APP_ID = "DRAFTINE_APP_ID"
}

enum class BuildTypes(
    val title: String,
    val isMinifyEnabled: Boolean,
    val keystoreFileName: String = "config/signing/draftine-debug.jks"
) {
    DEBUG(title = "debug", isMinifyEnabled = false),
    RELEASE(title = "release", isMinifyEnabled = true)
}