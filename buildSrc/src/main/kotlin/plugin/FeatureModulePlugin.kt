package plugin

import AndroidDependencies.navigation
import AndroidDependencies.ui
import CoreModules.archModule
import CoreModules.iconsModule
import CoreModules.uiModule
import CoreModules.utilsModule
import DIDependencies.koin
import LintDependencies.lintChecks
import MiscDependencies.lottie
import Plugins
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class FeatureModulePlugin : AndroidModulePlugin() {

    override fun apply(project: Project) {
        super.apply(project)
        project.run {
            applyPlugins()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(plugin = Plugins.kotlinKapt)
        }
    }

    private fun Project.applyDependencies() {
        dependencies.apply {
            lintChecks()

            ui()
            lottie()
            koin()
            navigation()
            archModule()
            uiModule()
            iconsModule()
            utilsModule()
        }
    }
}