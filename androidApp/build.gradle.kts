import java.util.Properties

plugins {
    id("configure-application")
    alias(libs.plugins.android.application)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

apply(from = "../common-android-compose.gradle")

val appVersionName: String by rootProject.extra
val appVersionCode: Int by rootProject.extra



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
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.tooling.preview)

    implementation(projects.composeApp)
}