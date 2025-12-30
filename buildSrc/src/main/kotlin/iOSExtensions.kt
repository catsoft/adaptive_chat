@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

fun Project.configureIosKmpTargets(
    // device, simulator
    targetsProperty: String = "iosTargets",
    // prod, debug
    targetsDestination: String = "iosTargetsDestination",
    withBaseName: Boolean = true
) {
    //todo need to think about logic
    // https://github.com/ktorio/ktor/blob/931540fd94687d296d42900756318a1a81cc928e/gradle.properties
    configure<KotlinMultiplatformExtension> {
        val targetsList = project.findProperty(targetsProperty)?.toString()?.split(",")?.map { it.trim() }.orEmpty()
        val targetDestination = project.findProperty(targetsDestination)?.toString()

        targetsList.forEach { targetName ->
            val target = when (targetName) {
                "device" -> iosArm64()
                "simulator" -> iosSimulatorArm64()
                else -> {
                    logger.warn("Unknown iOS target: $targetName. Supported: arm64, simArm64")
                    return@forEach
                }
            }

            configureIosTarget(this@configureIosKmpTargets, target, withBaseName, targetDestination)
        }
    }
}

private fun configureIosTarget(
    project: Project,
    target: KotlinNativeTarget,
    withBaseName: Boolean,
    targetDestination: String?,
) {
    target.binaries.framework {
        configureIosBaseName(project, this, withBaseName)
        isStatic = true

        when (targetDestination) {
            "prod" -> {
                freeCompilerArgs += listOf("-opt")
            }

            "debug" -> {
                freeCompilerArgs += listOf("-g")
            }
        }
    }
}

private fun configureIosBaseName(project: Project, target: Framework, withBaseName: Boolean) = with(project) {
    if (withBaseName) {
        val moduleNamespace = getFormattedName()
        println(moduleNamespace)
        target.baseName = moduleNamespace
    } else {
        target.baseName = "ComposeApp"
    }
}


fun Project.configureCocoaPodsIfEnabled(
    podspecName: String = "IosDependencies",
    version: String = "1.2",
    deploymentTarget: String = "18.2",
    summary: String = "Shared module for Adaptive Chat",
    frameworkBaseName: String = "shared",
    isStatic: Boolean = true,
    pods: List<String> = emptyList()
) {
    val targets = project.findProperty("targets")?.toString()?.split(",")?.map { it.trim() }.orEmpty()
    
    if (!targets.contains("ios")) {
        println("iOS target disabled - skipping CocoaPods configuration for ${project.name}")
        return
    }
    
    println("iOS target enabled - applying CocoaPods plugin for ${project.name}")
    
    pluginManager.apply("org.jetbrains.kotlin.native.cocoapods")
    
    configure<KotlinMultiplatformExtension> {
        configure<CocoapodsExtension>() {
            this.version = version
            ios.deploymentTarget = deploymentTarget
            this.summary = summary
            name = podspecName

            pods.forEach { podName ->
                pod(podName)
            }

            framework {
                this.isStatic = isStatic
                baseName = frameworkBaseName
            }
        }
    }
}