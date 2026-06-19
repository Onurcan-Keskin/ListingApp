import com.onurcan.bundle
import com.onurcan.implementation
import com.onurcan.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidCoreConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply("com.onurcan.android.library")
            }
            dependencies {
                implementation(library("androidx-core-ktx"))
                implementation(library("androidx-lifecycle-runtime-ktx"))
                implementation(library("androidx-appcompat"))
                implementation(library("kotlin-serialization"))
                implementation(bundle("compose-ui"))
                implementation(library("coil"))
                implementation(library("coil-svg"))
                implementation(library("kotlinx-coroutines-rx3"))
                implementation(bundle("rx"))
            }
        }
    }
}