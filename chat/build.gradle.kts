plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    id("configure-library")
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

apply(from = "../common-module-compose.gradle")

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.chatApi)
            api(projects.agentApi)
            api(projects.conversationApi)
            implementation(projects.aiService)
            implementation(projects.uiKit)
            implementation(projects.shared)
        }
    }
}