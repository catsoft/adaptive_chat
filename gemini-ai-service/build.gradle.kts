plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    id("configure-library")
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

apply(from = "../common-ktor.gradle")
apply(from = "../common-module-compose.gradle")

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.aiService)
            api(projects.chatApi)
            implementation(projects.shared)
        }
    }
}
