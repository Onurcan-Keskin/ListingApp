package com.onurcan

import com.android.build.api.dsl.CommonExtension
import com.onurcan.KotlinAndroid.javaVersion
import com.onurcan.KotlinAndroid.jvmVersion
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

object KotlinAndroid {
    val javaVersion = JavaVersion.VERSION_17
    val jvmVersion = JvmTarget.JVM_17
}

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension) {
    commonExtension.apply {
        compileSdk = 36

        defaultConfig.apply {
            minSdk = 26
        }
        lint.apply { disable += "ResourceName" }
        buildFeatures.apply { buildConfig = true }

        compileOptions.apply {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        configureKotlin<KotlinAndroidProjectExtension>()
    }
}

private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    val warningErrors: String? by project
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported Kotlin Gradle Plugin, ${T::class}")
    }.apply {
        jvmTarget.set(jvmVersion)
        allWarningsAsErrors.set(warningErrors.toBoolean())
        freeCompilerArgs.add(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}
