import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.resources.ResourcesExtension

val org.gradle.api.artifacts.dsl.DependencyHandler.compose: ComposePlugin.Dependencies
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

val org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.`compose`: ComposePlugin.Dependencies
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

fun Project.configureTargets(withBaseName: Boolean = true) {
    val targets = project.findProperty("targets")?.toString()?.split(",")?.map { it.trim() }.orEmpty()

    if (targets.contains("android")) {
        configureAndroidKmpTarget(isLibrary = withBaseName)
    }
    if (targets.contains("ios")) {
        configureIosKmpTargets(withBaseName = withBaseName)
    }
}

fun Project.configureModuleNamespace(isLibrary: Boolean) {
    val namespace = "com.adaptivechat.${getFormattedName()}"
    extensions.findByType<com.android.build.gradle.BaseExtension>()?.apply {
        this.namespace = namespace
    }

    if (isLibrary) {
        setAndroidLibraryNamespace(namespace = namespace)
    }

    println("Module ${project.name} -> namespace: ${getFormattedName()}")
}


fun Project.configureComposeResources() {
    pluginManager.withPlugin("org.jetbrains.compose") {
        extensions.getByType<ComposeExtension>().extensions.configure<ResourcesExtension>() {
            publicResClass = true
            packageOfResClass = "com.adaptivechat.${this@configureComposeResources.getFormattedName()}.resources"
            generateResClass = always
        }
    }

    println("Compose resources package: com.adaptivechat.${getFormattedName()}.resources")
}

fun Project.getFormattedName(): String = when {
    hasProperty("moduleNamespace") -> property("moduleNamespace") as String
    else -> name.replace("-", "_")
}.lowercase()