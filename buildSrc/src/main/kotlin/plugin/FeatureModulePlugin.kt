package plugin

import AndroidDependencies.navigation
import AndroidDependencies.ui
import DIDependencies.koin
import Plugins
import internal.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.project

class FeatureModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(plugin = Plugins.androidModule)
            apply(plugin = Plugins.kotlinKapt)
        }
    }

    private fun Project.applyDependencies() {
        dependencies.apply {
            ui()
            koin()
            navigation()
            implementation(project(":arch"))
        }
    }
}