repositories {
    mavenLocal()
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

kotlin {
    jvmToolchain(24)
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.compose.compiler.gradle)
    implementation(libs.compose.gradle)
}

gradlePlugin {
    plugins {
        register("configure-application") {
            id = "configure-application"
            implementationClass = "ConfigureAndroidApplication"
        }
        register("configure-library") {
            id = "configure-library"
            implementationClass = "ConfigureMultiplatformLibrary"
        }
    }
}