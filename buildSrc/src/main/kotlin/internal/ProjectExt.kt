package internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import java.util.Locale

private const val MAX_DEPTH = 10
private const val ANDROID_EXTENSION_NAME = "android"
private const val QUALITY_REPORT_DIR = "build/quality"

internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.findByName(ANDROID_EXTENSION_NAME) as ApplicationExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.findByName(ANDROID_EXTENSION_NAME) as LibraryExtension

internal val Project.androidExtension: CommonExtension<*, *, *, *>
    get() = extensions.findByName(ANDROID_EXTENSION_NAME) as CommonExtension<*, *, *, *>

internal fun Project.configureProjectModules() {
    val projectName = name
    dependencies.apply {
        rootDir.walk()
            .maxDepth(MAX_DEPTH)
            .filter { it.isGradleModule && it.isBuildModule.not() && it.name != projectName && it.name != "buildSrc" }
            .forEach { module ->
                val moduleName = ":${module.name}"
                implementation(project(moduleName))
                println("module \"$moduleName\" attached to $projectName")
            }
    }
}

internal fun Project.setupQualityCheck() = apply {
    tasks.withType<Detekt>().configureEach {
        config.setFrom("$rootDir/config/quality/detekt-config.yml")

        reports {
            html.required.set(true)
            txt.required.set(true)
            xml.required.set(false)
            sarif.required.set(false)

            html.outputLocation.set(file("$QUALITY_REPORT_DIR/detekt/detekt-report.html"))
            txt.outputLocation.set(file("$QUALITY_REPORT_DIR/detekt/detekt-report.txt"))
        }
    }

    androidExtension.apply {
        lint {
            lintConfig = file("$rootDir/config/quality/lint-config.xml")

            htmlReport = true
            textReport = true
            xmlReport = false
            sarifReport = false

            htmlOutput = file("$QUALITY_REPORT_DIR/lint/lint-report.html")
            textOutput = file("$QUALITY_REPORT_DIR/lint/lint-report.txt")

            disable += listOf(
                "RtlSymmetry",
                "RtlHardcoded",
                "InvalidPackage",
                "ParcelCreator",
                "AppLinkUrlError",
                "MissingTranslation",
                "DuplicatePlatformClasses",
                "CheckResult",
                "Instantiatable"
            )
        }
    }
    tasks.register("qualityCheck").configure {
        group = "verification"
        description = "Inspect your code before push"
        dependsOn("lint", "detekt")
    }
}

internal fun Project.setupDependencyUpdatesTask() = apply {
    tasks.withType<DependencyUpdatesTask> {
        rejectVersionIf {
            isNonStable(candidate.version)
        }

        gradleReleaseChannel = "current"
        checkForGradleUpdate = true
        outputFormatter = "html"
        outputDir = "$QUALITY_REPORT_DIR/dependency-updates"
        reportfileName = "dependency-updates-report"
    }
}

private fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.getDefault()).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}