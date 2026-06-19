plugins {
    alias(libs.plugins.onurcan.library)
}

android {
    namespace = "com.onurcan.core.data"

}

dependencies {
    api(project(":core:database"))
    api(project(":core:common"))
    api(project(":core:network"))

    implementation(libs.bundles.rx)
}