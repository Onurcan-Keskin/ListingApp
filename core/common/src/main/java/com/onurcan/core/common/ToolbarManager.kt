package com.onurcan.core.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ToolbarManager {
    // Stores configurations mapped by the navigation key type (e.g., ListingNavKey)
    val screens = mutableStateMapOf<Any, ToolbarConfig>()

    data class ToolbarConfig(
        val title: @Composable () -> Unit = {},
        val navigationIcon: (@Composable () -> Unit)? = null,
        val actions: (@Composable RowScope.() -> Unit)? = null
    )
}