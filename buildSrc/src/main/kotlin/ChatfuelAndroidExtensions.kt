@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi


fun Project.configureAndroidBase() {
    configure<ApplicationExtension> {
        println("set android base kotlin extension")

        compileSdk = Configs.Android.COMPILE_SDK

        defaultConfig {
            minSdk = Configs.Android.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_24
            targetCompatibility = JavaVersion.VERSION_24
        }
        println("Android config applied to ${project.name}")
    }
}
