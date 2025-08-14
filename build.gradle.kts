plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.kotlinSpring) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.springBoot) apply false
    alias(libs.plugins.springDependencyManagement) apply false
}

allprojects {
    afterEvaluate {
        configurations
            .flatMap { config ->
                config.hierarchy
                    .filter { it.name.startsWith("commonMain") }
                    .flatMap { it.dependencies.filterIsInstance<ModuleDependency>() }
            }
            .distinct()
            .forEach {
                it.exclude(group = "org.jetbrains.compose.foundation")
                it.exclude(group = "org.jetbrains.compose.material3")
                it.exclude(
                    group = "org.jetbrains.compose.ui",
                    module = "ui"
                )
                it.exclude(
                    group = "org.jetbrains.androidx.navigation",
                    module = "navigation-compose"
                )
            }
    }
}