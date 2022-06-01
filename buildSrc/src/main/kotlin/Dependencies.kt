@file:Suppress("MemberVisibilityCanBePrivate")

import CoreModules.koinModules
import internal.compileOnly
import internal.implementation
import internal.kapt
import internal.lintChecks
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

const val kotlinVersion = "1.6.21"

object Plugins {

    const val applicationModule = "application-module-plugin"
    const val featureModule = "feature-module-plugin"
    const val apiModule = "api-module-plugin"
    const val androidModule = "android-module-plugin"
    const val kotlinModule = "kotlin-module-plugin"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"

    const val kotlin = "kotlin"
    const val kotlinJVM = "org.jetbrains.kotlin.jvm"
    const val kotlinKapt = "kotlin-kapt"
    const val javaLibrary = "java-library"

    const val navigation = "androidx.navigation.safeargs.kotlin"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val dependenciesVersions = "com.github.ben-manes.versions"
}

object KotlinDependencies {

    object Versions {
        const val coroutines = "1.6.2"
    }

    const val stdLibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    fun DependencyHandler.stdLib() = apply {
        implementation(stdLibJdk8)
    }

    fun DependencyHandler.coroutines() = apply {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(coroutinesTest)
    }
}

object AndroidDependencies {

    object Versions {
        const val appCompat = "1.4.1"
        const val core = "1.7.0"
        const val material = "1.6.1"
        const val constraintLayout = "2.1.4"
        const val lifecycle = "2.4.1"
        const val recyclerView = "1.2.1"
        const val navigation = "2.4.2"
        const val fragment = "1.4.1"
        const val browser = "1.3.0"
    }

    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val browser = "androidx.browser:browser:${Versions.browser}"

    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    fun DependencyHandler.ui() = apply {
        implementation(coreKtx)
        implementation(fragment)
        implementation(material)
        implementation(appCompat)
        implementation(recyclerView)
        implementation(constraintLayout)
    }

    fun DependencyHandler.navigation() = apply {
        implementation(navigationFragment)
        implementation(navigationRuntime)
        implementation(navigationUi)
    }

    fun DependencyHandler.applyBrowser() = apply {
        implementation(browser)
    }
}

object DIDependencies {
    object Versions {
        const val koin = "3.2.0"
    }

    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"

    fun DependencyHandler.koin() = apply {
        implementation(core)
        implementation(android)
        koinModules()
    }
}

object NetworkDependencies {

    object Versions {
        const val moshi = "1.13.0"
        const val okhttp = "4.9.3"
        const val retrofit = "2.9.0"
        const val rssParser = "4.0.2"
    }

    object Okhttp {
        val client = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Moshi {
        val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        val compiler = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object RssParser {
        val rssParser = "com.prof18.rssparser:rssparser:${Versions.rssParser}"
    }

    fun DependencyHandler.okhttp() = apply {
        implementation(Okhttp.client)
        implementation(Okhttp.logging)
    }

    fun DependencyHandler.retrofit() = apply {
        implementation(Retrofit.retrofit)
        implementation(Retrofit.moshiConverter)
    }

    fun DependencyHandler.moshi() = apply {
        implementation(Moshi.moshi)
        implementation(Moshi.kotlin)
        kapt(Moshi.compiler)
    }

    fun DependencyHandler.rssParser() = apply {
        implementation(RssParser.rssParser)
    }

    fun DependencyHandler.network() = apply {
        okhttp()
        retrofit()
        moshi()
        rssParser()
    }
}

object MiscDependencies {

    object Versions {
        const val lottie = "5.1.1"
        const val coil = "2.1.0"
        const val joda = "2.10.14"
    }

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val joda = "joda-time:joda-time:${Versions.joda}"

    object Coil {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val coilGif = "io.coil-kt:coil-gif:${Versions.coil}"
        const val coilSvg = "io.coil-kt:coil-svg:${Versions.coil}"
        const val coilVideo = "io.coil-kt:coil-video:${Versions.coil}"
    }

    fun DependencyHandler.lottie() = apply {
        implementation(lottie)
    }

    fun DependencyHandler.coil() = apply {
        implementation(Coil.coil)
        implementation(Coil.coilGif)
        implementation(Coil.coilSvg)
        implementation(Coil.coilVideo)
    }

    fun DependencyHandler.applyJodaTime() = apply {
        implementation(joda)
    }
}

object AnnotationProcessingDependencies {

    object Versions {
        const val autoService = "1.0.1"
    }

    val autoService = "com.google.auto.service:auto-service:${Versions.autoService}"
    val fixGuavaPackage = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"

    fun DependencyHandler.annotationProcessing() = apply {
        kapt(autoService)
        implementation(autoService)
        kapt(fixGuavaPackage)
        implementation(fixGuavaPackage)
    }
}

object LintDependencies {

    object Versions {
        const val lint = "27.1.1"
    }

    const val lintApi = "com.android.tools.lint:lint-api:${Versions.lint}"
    const val lintChecks = "com.android.tools.lint:lint-checks:${Versions.lint}"

    fun DependencyHandler.lint() = apply {
        compileOnly(lintApi)
        compileOnly(lintChecks)
    }

    fun DependencyHandler.lintChecks() = apply {
        lintChecks(project(":lint"))
    }
}

internal object CoreModules {

    fun DependencyHandler.archModule() = apply {
        implementation(project(":arch"))
    }

    fun DependencyHandler.uiModule() = apply {
        implementation(project(":ui"))
    }

    fun DependencyHandler.iconsModule() = apply {
        implementation(project(":icons"))
    }

    fun DependencyHandler.utilsModule() = apply {
        implementation(project(":utils"))
    }

    fun DependencyHandler.koinModules() = apply {
        implementation(project(":koin-module-annotation"))
        kapt(project(":koin-module-annotation-processor"))
    }
}