plugins {
    alias(libs.plugins.onurcan.feature.impl)
}

android {
    namespace = "com.onurcan.feature.listing.impl"
}

dependencies {
    implementation(project(":feature:listing:api"))
    implementation(project(":feature:detail:api"))
}