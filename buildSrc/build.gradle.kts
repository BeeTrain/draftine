import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion = JavaVersion.VERSION_11
val kotlinVersion = "1.7.10"
val buildGradlePluginVersion = "7.2.1"
val navigationVersion = "2.4.2"
val detektVersion = "1.19.0"
val dependenciesVersion = "0.42.0"
val googleServicesVersion = "4.3.10"
val appDistributionVersion = "3.0.2"
val crashlyticsPlugin = "2.9.1"

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
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
    implementation("com.android.tools.build:gradle:$buildGradlePluginVersion")

    implementation("com.google.gms:google-services:$googleServicesVersion")
    implementation("com.google.firebase:firebase-appdistribution-gradle:$appDistributionVersion")
    implementation("com.google.firebase:firebase-crashlytics-gradle:$crashlyticsPlugin")

    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependenciesVersion")
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