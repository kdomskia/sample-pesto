import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

val appId = "io.kdomskia.sample.pesto"

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop") {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass = "$appId.MainKt"
        }
    }

    js {
        outputModuleName.set("composeApp")
        useEsModules()
        browser {
            val outputFile = "composeApp.js"
            runTask {
                mainOutputFileName = outputFile
            }
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
                outputFileName = outputFile
            }
        }
        binaries.executable()
        compilerOptions {
            target.set("es2015")
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kdomskia.ui)
            implementation(libs.kdomskia.material3)
            implementation(libs.kdomskia.navigation)
            implementation(libs.material3.adaptive)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.ktor.core)
            implementation(libs.ktor.ktxJson)
            implementation(libs.ktor.resources)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(compose.components.resources)
        }

        val domMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                //Re-adding Kdomskia dependencies to restore previously removed AndroidX artifacts in root build.gradle.kts
                implementation(libs.kdomskia.ui)
                implementation(libs.kdomskia.material3)
                implementation(libs.kdomskia.navigation)
                implementation(libs.kotlinx.coroutines.js)
                implementation(devNpm("compression-webpack-plugin", libs.versions.compressionWebpackPlugin))
            }
        }

        val skiaMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                //Re-adding Kdomskia dependencies to restore previously removed AndroidX artifacts in root build.gradle.kts
                implementation(libs.kdomskia.ui)
                implementation(libs.kdomskia.material3)
                implementation(libs.kdomskia.navigation)
                implementation(libs.coil.network.ktor3)
            }
        }

        androidMain {
            dependsOn(skiaMain)
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.ktor.android)
            }
        }

        named("desktopMain") {
            dependsOn(skiaMain)
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.ktor.java)
            }
        }

        iosMain {
            dependsOn(skiaMain)
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }

        jsMain {
            dependsOn(domMain)
        }
    }
}

android {
    namespace = appId
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = appId
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose {
    resources {
        packageOfResClass = appId
    }
    desktop {
        application {
            mainClass = "$appId.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = appId
                packageVersion = "1.0.0"
            }
        }
    }
}

fun KotlinDependencyHandler.npm(
    name: String,
    version: Provider<String>
): Dependency = npm(
    name = name,
    version = version.get()
)

fun KotlinDependencyHandler.devNpm(
    name: String,
    version: Provider<String>
): Dependency = devNpm(
    name = name,
    version = version.get()
)