import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val DependencyHandler.compose: ComposePlugin.Dependencies
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

val KotlinMultiplatformExtension.`compose`: ComposePlugin.Dependencies
    get() =
        (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

fun Project.configureTargets() {
    val targets = project.findProperty("targets")?.toString()?.split(",")?.map { it.trim() }.orEmpty()

    if (targets.contains("android")) {
        configureAndroidKmpTarget()
    }
    if (targets.contains("ios")) {
        configureIosKmpTargets()
    }
}

fun Project.configureModuleNamespace() {
    val namespace = "com.adaptivechat.${getFormattedName()}"
//    extensions.findByType<CommonExtension>()?.apply {
//        this.namespace = namespace
//    }

    configure<KotlinMultiplatformExtension> {
        configure<KotlinMultiplatformAndroidLibraryExtension> {
            this.namespace = namespace
        }
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
}.lowercase().replace("composeapp", "ComposeApp")