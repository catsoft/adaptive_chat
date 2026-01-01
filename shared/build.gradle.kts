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
apply(from = "../common-pagings.gradle")
apply(from = "../common-connectivity.gradle")
apply(from = "../common-ktor.gradle")

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.sharedApi)
            api(projects.localization)
            api(projects.uiKit)
            api(projects.uiKitApi)

            api(libs.kmp.media)
            api(libs.kmp.media.compose)

            api(libs.libphonenumber)
            api(libs.kmp.locale)
            api(libs.kmp.permissions)

            api(libs.kmp.reveal)
            api(libs.kmp.reach.editor)
            api(libs.kmp.uri)
            api(libs.room.runtime)

            api(libs.kotlinx.datetime)

            api(libs.human.readable)

            api(libs.ui.backhandler)

            api(libs.shimmer)

            api(libs.kmp.coil.compose)
            api(libs.kmp.coil.network.core)
            api(libs.kmp.coil.network.ktor3)

            api(libs.compottie)
            api(libs.compottie.dot)
            api(libs.compottie.network)
            api(libs.compottie.resources)

            api(libs.kmp.currency)
            api(libs.kmp.country)

            api(libs.kmp.permissions)
            api(libs.kmp.permissions.compose)
            api(libs.kmp.permissions.bluetooth)
            api(libs.kmp.permissions.camera)
            api(libs.kmp.permissions.contacts)
            api(libs.kmp.permissions.gallery)
            api(libs.kmp.permissions.location)
            api(libs.kmp.permissions.microphone)
            api(libs.kmp.permissions.motion)
            api(libs.kmp.permissions.notifications)
            api(libs.kmp.permissions.storage)

            api(libs.bundles.fileKit)
        }

        androidMain.dependencies {
            implementation(libs.androidx.security.crypto.ktx)

            implementation(libs.androidx.browser)
            implementation(libs.android.browser.helper)
        }
    }
}
