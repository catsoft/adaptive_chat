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

            implementation(libs.graphql.java)
        }

        commonMain.dependencies {
            api(projects.localization)
            implementation(projects.logger)

            implementation(projects.uiKit)
            implementation(projects.uiKitApi)

            implementation(projects.shared)
            implementation(projects.sharedApi)

            implementation(projects.chat)
            implementation(projects.chatApi)

            implementation(projects.agent)
            implementation(projects.agentApi)

            implementation(projects.conversation)
            implementation(projects.conversationApi)

            implementation(projects.aiService)
            implementation(projects.geminiAiService)

            implementation(libs.bundles.compose.full)

            implementation(libs.serialization.json)
            implementation(libs.kotlinx.datetime)

            implementation(libs.alert.kmp)
        }
    }
}

android {
    namespace = "com.catsoft.adaptivechat"
    
    sourceSets {
        getByName("main") {
            assets.srcDirs(
                layout.buildDirectory.dir("generatedLocalizationRes")
            )
        }
    }

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

val copyLocalizationResources = tasks.register<Copy>("copyLocalizationResources") {
    dependsOn(":localization:prepareComposeResourcesTaskForCommonMain")
    
    from(project(":localization").file("build/generated/compose/resourceGenerator/preparedResources/commonMain/composeResources"))
    into(layout.buildDirectory.dir("generatedLocalizationRes/composeResources/com.adaptivechat.localization.resources"))
}

afterEvaluate {
    tasks.matching { it.name.startsWith("merge") && it.name.endsWith("Assets") }.configureEach {
        dependsOn(copyLocalizationResources)
    }
}
