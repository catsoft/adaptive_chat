plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").version("2.3.0").apply(false)
    kotlin("plugin.serialization").version("2.3.0").apply(false)
    id("com.android.application").version("9.0.0-rc01").apply(false)
    id("com.android.kotlin.multiplatform.library").version("9.0.0-rc01").apply(false)
    id("org.jetbrains.compose").version("1.10.0-rc02").apply(false)
    id("org.jetbrains.kotlin.plugin.compose").version("2.3.0").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
