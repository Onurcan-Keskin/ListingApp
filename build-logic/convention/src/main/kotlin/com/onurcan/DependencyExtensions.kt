package com.onurcan

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(notation: Any) {
    add("implementation", notation)
}

internal fun DependencyHandler.api(notation: Any) {
    add("api", notation)
}

internal fun DependencyHandler.debugImplementation(notation: Any) {
    add("debugImplementation", notation)
}

internal fun DependencyHandler.testImplementation(notation: Any) {
    add("testImplementation", notation)
}

internal fun DependencyHandler.androidTestImplementation(notation: Any) {
    add("androidTestImplementation", notation)
}

internal fun DependencyHandler.ksp(notation: Any) {
    add("ksp", notation)
}