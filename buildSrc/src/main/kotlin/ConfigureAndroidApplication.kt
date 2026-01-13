import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigureAndroidApplication : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.withPlugin("com.android.application") {
            configureAndroidBase()
        }
    }
}
