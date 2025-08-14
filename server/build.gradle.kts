plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
}

group = "io.kdomskia.sample.pesto"
version = "1.0.0"

dependencies {
    implementation(projects.shared)
    implementation(libs.springBoot.starter.web)
    implementation(libs.kotlin.reflect)
}