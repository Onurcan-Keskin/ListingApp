package com.onurcan

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.library(libraryName: String) = libs.findLibrary(libraryName).get()

internal fun Project.bundle(bundleName: String) = libs.findBundle(bundleName).get()

internal fun Project.plugin(plugin: String) = libs.findPlugin(plugin).get()