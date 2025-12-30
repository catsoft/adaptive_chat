import com.android.build.api.dsl.androidLibrary
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.application)
    id("configure-application")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

apply(from = "../common-pagings.gradle")
apply(from = "../common-connectivity.gradle")
apply(from = "../common-module-compose.gradle")
apply(from = "../common-ktor.gradle")

val appVersionName: String by rootProject.extra
val appVersionCode: Int by rootProject.extra


kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.installreferrer)
            implementation(libs.androidx.profileinstaller)

            implementation(libs.auth)
            implementation(libs.auth.play)
            implementation(libs.auth.identity)
            implementation(libs.gms.auth)

            implementation(libs.graphql.java)
        }

        commonMain.dependencies {
//            api(":shared")

            implementation(libs.bundles.compose.full)

            implementation(libs.apollo.runtime)
            implementation(libs.apollo.normalized.cache)
            implementation(libs.apollo.normalized.cache.sqlite)

            implementation(libs.serialization.json)
            implementation(libs.kotlinx.datetime)

            implementation(libs.alert.kmp)
        }
    }
}

android {
    namespace = "com.catsoft.adaptivechat"

    defaultConfig {
        multiDexEnabled = true
        applicationId = "com.catsoft.adaptivechat"
        versionCode = appVersionCode
        versionName = appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // todo signing and build types
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}