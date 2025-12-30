plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    jvmToolchain(24)
    
    androidLibrary {
        namespace = "com.catsoft.adaptivechat"
        compileSdk = 36
        minSdk = 24
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:1.10.0-rc02")
            implementation("org.jetbrains.compose.foundation:foundation:1.10.0-rc02")
            implementation("org.jetbrains.compose.material3:material3:1.10.0-rc02")
            implementation("org.jetbrains.compose.components:components-resources:1.10.0-rc02")
            
            // Ktor for networking
            implementation("io.ktor:ktor-client-core:2.3.5")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
            
            // Kotlinx serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            
            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            
            // ViewModel
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
        }
        
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:2.3.5")
            implementation("androidx.activity:activity-compose:1.8.0")
        }
        
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.5")
        }
    }
}
