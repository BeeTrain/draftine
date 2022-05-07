import LintDependencies.lint

plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlin)
    id(Plugins.kotlinJVM)
}

dependencies {
    lint()
}

java.sourceCompatibility = AppConfig.javaVersion
java.targetCompatibility = AppConfig.javaVersion

tasks {
    jar {
        manifest {
            attributes(
                mapOf("Lint-Registry-v2" to "dev.draftine.lint.CustomLintRegistry")
            )
        }
    }
}