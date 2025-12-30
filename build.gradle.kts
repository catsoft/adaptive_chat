buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        classpath(libs.kotlin.gradle)
        classpath(libs.android.gradle)
        classpath(libs.buildkonfig.gradle.plugin)
    }

    val appVersionName by extra("0.0.1")
    val appVersionCode by extra(1)
}

plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.jetbrains.kotlin.android) apply false
//    alias(libs.plugins.compose.compiler) apply false

//    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
//    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.baselineprofile) apply false
//
//    alias(libs.plugins.composeMultiplatform) apply false
//    alias(libs.plugins.kotlinMultiplatform) apply false
//    alias(libs.plugins.cocoaPods) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
