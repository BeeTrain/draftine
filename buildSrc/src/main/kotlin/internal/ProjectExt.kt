package internal

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.project

private const val MAX_DEPTH = 10

internal val Project.applicationExtension: ApplicationExtension
    get() = extensions.findByName("android") as ApplicationExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.findByName("android") as LibraryExtension

internal val Project.baseModulesDirectory
    get() = "$rootDir/source/modules/base"

internal val Project.featureModulesDirectory
    get() = "$rootDir/source/modules/feature"

fun Project.configureProjectModules() {
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