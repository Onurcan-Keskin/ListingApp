import com.onurcan.bundle
import com.onurcan.implementation
import com.onurcan.library
import com.onurcan.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.onurcan.android.library")
            }
            dependencies {
                implementation(project(":core:domain"))
                implementation(project(":core:data"))
                implementation(project(":core:model"))
                implementation(project(":core:database"))
                implementation(project(":core:common"))
                implementation(project(":core:ui"))

                implementation(library("androidx-lifecycle-compose"))
                implementation(library("androidx-compose-runtime"))
                implementation(library("androidx-lifecycle-viewModelCompose"))
                implementation(library("androidx-hilt-lifecycle-viewModelCompose"))
                implementation(library("androidx-navigation3-runtime"))
                implementation(library("androidx-navigation3-ui"))
                implementation(library("kotlinx-coroutines-rx3"))
                implementation(bundle("rx"))
            }
        }
    }
}