import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "com.onurcan.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.onurcan.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.onurcan.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCore") {
            id = "com.onurcan.android.core"
            implementationClass = "AndroidCoreConventionPlugin"
        }
        register("androidFeatureApi") {
            id = "com.onurcan.android.feature.api"
            implementationClass = "AndroidFeatureApiConventionPlugin"
        }
        register("androidFeatureImpl") {
            id = "com.onurcan.android.feature.impl"
            implementationClass = "AndroidFeatureImplConventionPlugin"
        }
        register("hiltCore") {
            id = "com.onurcan.hilt.core"
            implementationClass = "HiltConventionPlugin"
        }
        register("room") {
            id = "com.onurcan.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}
