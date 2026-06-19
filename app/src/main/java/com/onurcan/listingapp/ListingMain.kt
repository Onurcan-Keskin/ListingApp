package com.onurcan.listingapp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.onurcan.core.common.ToolbarManager
import com.onurcan.core.navigation.Navigator
import com.onurcan.core.navigation.rememberNavigationState
import com.onurcan.core.navigation.toEntries
import com.onurcan.feature.detail.impl.navigation.detailEntry
import com.onurcan.feature.listing.api.navigation.ListingNavKey
import com.onurcan.feature.listing.impl.navigation.listingEntry

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListingMain(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState(
        startKey = ListingNavKey,
        topLevelKeys = setOf(ListingNavKey),
    )
    val navigator = remember(navigationState) { Navigator(navigationState) }
    val currentKey = navigationState.currentKey
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            val config = ToolbarManager.screens[currentKey]
            config?.let {
                CenterAlignedTopAppBar(
                    title = {config.title() },
                    navigationIcon = { config.navigationIcon?.invoke() },
                    actions = { config.actions?.invoke(this) },
                    colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        titleContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                        scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        subtitleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }) { innerPadding ->
        SharedTransitionLayout {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                val entryProvider = entryProvider {
                    listingEntry(navigator, this@SharedTransitionLayout)
                    detailEntry(navigator, this@SharedTransitionLayout)
                }

                NavDisplay(
                    entries = navigationState.toEntries(entryProvider),
                    sceneStrategy = SinglePaneSceneStrategy(),
                    onBack = { navigator.goBack() },
                )
            }
        }
    }
}
