import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigureMultiplatformApplication : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        configureKmpCompilerOptions()
        configureAndroidKmpTarget(isLibrary = false)
        configureKotlinKeychain()
        configureAndroidBase()
        configureComposeResources()

        configureTargets(withBaseName = false)
    }
}