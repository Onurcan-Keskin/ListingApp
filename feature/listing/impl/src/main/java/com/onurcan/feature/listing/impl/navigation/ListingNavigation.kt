package com.onurcan.feature.listing.impl.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.onurcan.core.common.ToolbarManager
import com.onurcan.core.common.UiStatus
import com.onurcan.core.ui.screen.LoadingScreen
import com.onurcan.feature.listing.api.navigation.ListingNavKey
import com.onurcan.feature.listing.impl.event.ListingClickEvents
import com.onurcan.feature.listing.impl.screen.ListingScreen
import com.onurcan.feature.listing.impl.viewmodel.ListingViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListingNavigation(
    onTopicClick: (String) -> Unit,
    viewModel: ListingViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope
) {
    val uiData = viewModel.uiState.collectAsStateWithLifecycle()
    val animatedVisibilityScope = LocalNavAnimatedContentScope.current

    LaunchedEffect(Unit) {
        ToolbarManager.screens[ListingNavKey] = ToolbarManager.ToolbarConfig(
            title = {
                Text(
                    text = "Listing",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }

    when (val uiState = uiData.value.status) {
        UiStatus.Initial, UiStatus.Loading -> {
            LoadingScreen()
        }

        UiStatus.Success -> {
            ListingScreen(
                data = uiData.value.data,
                events = { event ->
                    when (event) {
                        is ListingClickEvents.DeletePost -> {
                            viewModel.deletePost(event.id)
                        }

                        is ListingClickEvents.GoToDetail -> {
                            onTopicClick(event.id)
                        }
                    }
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

        is UiStatus.Error -> {

        }
    }
}
