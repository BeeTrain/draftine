package internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType

private const val MAX_DEPTH = 10

internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.findByName("android") as ApplicationExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.findByName("android") as LibraryExtension

internal val Project.androidExtension: CommonExtension<*, *, *, *>
    get() = extensions.findByName("android") as CommonExtension<*, *, *, *>

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
    configure<DetektExtension> {
        config = files("$rootDir/config/quality/detekt-config.yml")
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
            txt.required.set(true)
            xml.required.set(false)
            sarif.required.set(false)

            html.outputLocation.set(file("build/quality/detekt/detekt-report.html"))
            txt.outputLocation.set(file("build/quality/detekt/detekt-report.txt"))
        }
    }

    androidExtension.apply {
        lint {
            lintConfig = file("$rootDir/config/quality/lint-config.xml")

            htmlReport = true
            textReport = true
            xmlReport = false
            sarifReport = false

            htmlOutput = file("build/quality/lint/lint-report.html")
            textOutput = file("build/quality/lint/lint-report.txt")

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