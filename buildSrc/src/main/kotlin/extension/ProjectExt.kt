package extension

import internal.implementation
import internal.isBuildModule
import internal.isGradleModule
import org.gradle.api.Project
import org.gradle.kotlin.dsl.project

private const val MAX_DEPTH = 10

fun Project.applyScreenModules() {
    val projectName = name
    dependencies.apply {
        rootDir.walk()
            .maxDepth(MAX_DEPTH)
            .filter {
                it.isGradleModule &&
                    it.isBuildModule.not() &&
                    it.name != projectName &&
                    it.name != "buildSrc" &&
                    it.name.endsWith("-screen")
            }
            .forEach { module ->
                val moduleName = ":${module.name}"
                implementation(project(moduleName))
                println("module \"$moduleName\" attached to $projectName")
            }
    }
}