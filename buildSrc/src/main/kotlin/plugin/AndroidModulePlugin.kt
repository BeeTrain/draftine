package plugin

import AppConfig
import BuildTypes
import Plugins
import internal.libraryExtension
import internal.setupQualityCheck
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

open class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyLibraryConfig()
            setupQualityCheck()
        }
    }

    private fun Project.applyPlugins() {
        apply(plugin = Plugins.androidLibrary)
        apply(plugin = Plugins.kotlinModule)
        apply(plugin = Plugins.detekt)
    }

    private fun Project.applyLibraryConfig() {
        libraryExtension.apply {
            compileSdk = AppConfig.compileSdkVersion

            defaultConfig {
                minSdk = AppConfig.minSdkVersion
                targetSdk = AppConfig.targetSdkVersion
            }

            buildTypes {
                BuildTypes.values().forEach { buildType ->
                    getByName(buildType.title) {
                        isMinifyEnabled = buildType.isMinifyEnabled
                    }
                }
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }
        }
    }
}