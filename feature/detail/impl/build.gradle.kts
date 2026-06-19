plugins {
    alias(libs.plugins.onurcan.feature.impl)
}

android {
    namespace = "com.onurcan.feature.detail.impl"

}

dependencies {
    implementation(project(":feature:detail:api"))
}