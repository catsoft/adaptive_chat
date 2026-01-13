@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val optInList = listOf(
    "androidx.compose.material3.ExperimentalMaterial3Api",
    "org.koin.core.annotation.KoinExperimentalAPI",
    "kotlinx.serialization.ExperimentalSerializationApi",
    "kotlinx.serialization.InternalSerializationApi",
    "androidx.compose.ui.ExperimentalComposeUiApi",
    "com.russhwolf.settings.ExperimentalSettingsApi",
    "com.russhwolf.settings.ExperimentalSettingsImplementation",
    "kotlinx.coroutines.ExperimentalCoroutinesApi",
    "kotlinx.cinterop.ExperimentalForeignApi",
    "kotlin.time.ExperimentalTime",
    "kotlinx.cinterop.BetaInteropApi"
)

fun Project.configureKmpCompilerOptions(
    extraOptIns: List<String> = emptyList(),
    extraFreeArgs: List<String> = emptyList()
) {
    configure<KotlinMultiplatformExtension> {
        compilerOptions {
            optIn.addAll(optInList)

            optIn.addAll(extraOptIns)

            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.addAll(extraFreeArgs)
        }
    }
}

fun Project.configureAndroidKmpTarget() {
    configure<KotlinMultiplatformExtension> {
        configure<KotlinMultiplatformAndroidLibraryTarget> {
            println("set android library target")
            compileSdk = Configs.Android.COMPILE_SDK
            minSdk = Configs.Android.MIN_SDK
        }
    }
}

fun Project.configureKotlinKeychain(
    version: Int = 24
) {
    configure<KotlinMultiplatformExtension> {
        jvmToolchain(version)
    }
}
