package plugin

import AndroidDependencies.navigation
import AndroidDependencies.ui
import AppConfig
import DIDependencies.koin
import Plugins
import internal.applicationExtension
import internal.configureProjectModules
import internal.getGitVersionCode
import internal.getGitVersionName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ApplicationModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            applyPlugins()
            applyApplicationConfig()
            applyDependencies()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(plugin = Plugins.androidApplication)
            apply(plugin = Plugins.kotlinModule)
        }
    }

    private fun Project.applyApplicationConfig() {
        applicationExtension.apply {
            compileSdk = AppConfig.compileSdkVersion
            buildToolsVersion = AppConfig.buildToolsVersion

            defaultConfig {
                applicationId = AppConfig.applicationId
                minSdk = AppConfig.minSdkVersion
                targetSdk = AppConfig.targetSdkVersion
                versionCode = getGitVersionCode()
                versionName = getGitVersionName()
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }
        }
    }

    private fun Project.applyDependencies() {
        dependencies.apply {
            configureProjectModules()

            ui()
            koin()
            navigation()
        }
    }
}