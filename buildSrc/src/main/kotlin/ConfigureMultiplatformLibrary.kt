
import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigureMultiplatformLibrary : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        configureModuleNamespace(isLibrary = true)
        configureKotlinKeychain()
        configureKmpCompilerOptions()
        configureAndroidBase()
        configureAndroidKmpTarget(isLibrary = true)

        configureTargets()

        configureComposeResources()
    }
}