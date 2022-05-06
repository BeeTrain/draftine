package plugin

import AndroidDependencies.navigation
import AndroidDependencies.ui
import CoreModules.archModule
import CoreModules.iconsModule
import CoreModules.uiModule
import DIDependencies.koin
import MiscDependencies.lottie
import Plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

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
            lottie()
            koin()
            navigation()
            archModule()
            uiModule()
            iconsModule()
        }
    }
}