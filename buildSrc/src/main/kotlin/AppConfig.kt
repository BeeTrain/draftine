import org.gradle.api.JavaVersion

@Suppress("unused")
object AppConfig {
    const val applicationId = "dev.draftine"

    const val compileSdkVersion = 32
    const val minSdkVersion = 26
    const val targetSdkVersion = 32
    const val buildToolsVersion = "30.0.2"

    val javaVersion = JavaVersion.VERSION_1_8
}

enum class BuildTypes(val title: String, val isMinifyEnabled: Boolean) {
    DEBUG(title = "debug", isMinifyEnabled = false),
    RELEASE(title = "release", isMinifyEnabled = true)
}