plugins {
    alias(libs.plugins.onurcan.core)
}

android {
    namespace = "com.onurcan.core.ui"
}

dependencies {
    implementation(libs.androidx.hilt.lifecycle.viewModelCompose)
}