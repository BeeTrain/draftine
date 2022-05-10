import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion = JavaVersion.VERSION_11
val kotlinVersion = "1.6.21"
val buildGradlePluginVersion = "7.2.0"
val navigationVersion = "2.4.2"
val detektVersion = "1.19.0"

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
}

project.tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = javaVersion.toString()
        apiVersion = kotlinVersion
    }
}

dependencies {
    compileOnly(gradleApi())

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("com.android.tools.build:gradle:$buildGradlePluginVersion")

    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
}

gradlePlugin {
    plugins.register("application-module-plugin") {
        id = "application-module-plugin"
        implementationClass = "plugin.ApplicationModulePlugin"
    }
    plugins.register("api-module-plugin") {
        id = "api-module-plugin"
        implementationClass = "plugin.ApiModulePlugin"
    }
    plugins.register("feature-module-plugin") {
        id = "feature-module-plugin"
        implementationClass = "plugin.FeatureModulePlugin"
    }
    plugins.register("android-module-plugin") {
        id = "android-module-plugin"
        implementationClass = "plugin.AndroidModulePlugin"
    }
    plugins.register("kotlin-module-plugin") {
        id = "kotlin-module-plugin"
        implementationClass = "plugin.KotlinModulePlugin"
    }
}