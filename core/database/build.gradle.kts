plugins {
    alias(libs.plugins.onurcan.core)
    alias(libs.plugins.onurcan.room)
}

android {
    namespace = "com.onurcan.core.database"
}

dependencies{
    implementation(project(":core:model"))

    implementation(libs.room.rxjava3)
}