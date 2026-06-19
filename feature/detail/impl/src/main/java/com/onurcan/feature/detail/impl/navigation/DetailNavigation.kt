package com.onurcan.feature.detail.impl.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.onurcan.core.common.ToolbarManager
import com.onurcan.core.common.UiStatus
import com.onurcan.core.navigation.Navigator
import com.onurcan.feature.detail.api.navigation.DetailNavKey
import com.onurcan.feature.detail.impl.events.DetailClickEvents
import com.onurcan.feature.detail.impl.screen.DetailScreen
import com.onurcan.feature.detail.impl.viewmodel.DetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
fun EntryProviderScope<NavKey>.detailEntry(
    navigator: Navigator,
    sharedTransitionScope: SharedTransitionScope
) {
    entry<DetailNavKey> { key ->
        DetailNavigation(
            id = key.id,
            onBack = { navigator.goBack() },
            sharedTransitionScope = sharedTransitionScope
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailNavigation(
    id: String,
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val animatedVisibilityScope = LocalNavAnimatedContentScope.current

    LaunchedEffect(id) {
        viewModel.loadPost(id)
    }

    LaunchedEffect(Unit) {
        ToolbarManager.screens[DetailNavKey(id)] = ToolbarManager.ToolbarConfig(
            title = {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier,
                    onClick = { onBack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        )
    }

    uiState.data.errorMessage?.let { error ->
        LaunchedEffect(error) {

        }
    }

    if (uiState.status == UiStatus.Success) {
        DetailScreen(
            post = uiState.data,
            events = { event ->
                when (event) {
                    is DetailClickEvents.UpdateTitle -> {
                        viewModel.updateTitle(id, event.title)
                    }

                    is DetailClickEvents.UpdateBody -> {
                        viewModel.updateBody(id, event.body)
                    }
                }
            },
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}
