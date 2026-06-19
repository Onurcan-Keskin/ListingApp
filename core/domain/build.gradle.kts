plugins {
    alias(libs.plugins.onurcan.core)
}

android {
    namespace = "com.onurcan.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
}