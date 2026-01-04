plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    id("configure-library")
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.room)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.atomic)
}

apply(from = "../common-module-compose.gradle")
apply(from = "../common-pagings.gradle")
apply(from = "../common-connectivity.gradle")

kotlin {

    sourceSets {
        commonMain.dependencies {
            api(projects.uiKitApi)
            api(projects.logger)

            api(libs.ui.backhandler)

            api(libs.room.runtime)

            api(libs.shimmer)

            api(libs.serialization.json)
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}