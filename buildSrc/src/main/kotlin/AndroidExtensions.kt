@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.android.build.api.dsl.androidLibrary
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureKmpCompilerOptions(
    extraOptIns: List<String> = emptyList(),
    extraFreeArgs: List<String> = emptyList()
) {
    configure<KotlinMultiplatformExtension> {
        compilerOptions {
            optIn.addAll(
                "androidx.compose.material3.ExperimentalMaterial3Api",
                "org.koin.core.annotation.KoinExperimentalAPI",
                "kotlinx.serialization.ExperimentalSerializationApi",
                "kotlinx.serialization.InternalSerializationApi",
                "androidx.compose.ui.ExperimentalComposeUiApi",
                "kotlin.time.ExperimentalTime",
                "com.russhwolf.settings.ExperimentalSettingsApi",
                "com.russhwolf.settings.ExperimentalSettingsImplementation",
                "kotlinx.coroutines.ExperimentalCoroutinesApi",
                "kotlinx.cinterop.ExperimentalForeignApi",
                "kotlinx.cinterop.BetaInteropApi"
            )

            optIn.addAll(extraOptIns)

            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.addAll(extraFreeArgs)
        }
    }
}

fun Project.configureAndroidKmpTarget(jvmTarget: JvmTarget = JvmTarget.JVM_24, isLibrary: Boolean) {
    configure<KotlinMultiplatformExtension> {
        if (isLibrary) {
            println("set android library target")
            androidLibrary {
                compileSdk = Configs.Android.COMPILE_SDK
                compilerOptions {
                    this.jvmTarget.set(jvmTarget)
                }
            }
        } else {
            println("set android target")
            androidTarget {
                compilerOptions {
                    this.jvmTarget.set(jvmTarget)
                }
            }
        }
    }
}

fun Project.setAndroidLibraryNamespace(namespace: String) {
    configure<KotlinMultiplatformExtension> {
        androidLibrary {
            this.namespace = namespace
        }
    }
}

fun Project.configureKotlinKeychain(
    version: Int = 24
) {
    extensions.configure<KotlinMultiplatformExtension> {
        jvmToolchain(version)
    }
}

fun Project.configureAndroidBase() {
    extensions.findByType<BaseExtension>()?.apply {
        compileSdkVersion(Configs.Android.COMPILE_SDK)

        defaultConfig {
            minSdk = Configs.Android.MIN_SDK
            targetSdk = Configs.Android.TARGET_SDK
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_24
            targetCompatibility = JavaVersion.VERSION_24
        }
    }

    println("Android config applied to ${project.name}")
}