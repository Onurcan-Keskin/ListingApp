plugins {
    alias(libs.plugins.onurcan.library)
    alias(libs.plugins.onurcan.room)
}

android {
    namespace = "com.onurcan.core.common"
}

dependencies {
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.androidx.hilt.lifecycle.viewModelCompose)
    api(libs.bundles.rx)
}