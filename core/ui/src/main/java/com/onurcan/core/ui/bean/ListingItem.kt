package com.onurcan.core.ui.bean

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.onurcan.core.ui.CoilAsyncImage
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private enum class DragValue {
    Closed,
    Open
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListingItem(
    modifier: Modifier = Modifier,
    id: Int,
    title: String,
    body: String,
    onDelete: () -> Unit = {},
    onClick: () -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    val drawerWidthPx = with(density) { 140.dp.toPx() }

    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = DragValue.Closed,
            anchors = DraggableAnchors {
                DragValue.Closed at 0f
                DragValue.Open at -drawerWidthPx
            },
            positionalThreshold = { distance -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = tween(durationMillis = 300),
            decayAnimationSpec = exponentialDecay()
        )
    }

    LaunchedEffect(drawerWidthPx) {
        draggableState.updateAnchors(
            DraggableAnchors {
                DragValue.Closed at 0f
                DragValue.Open at -drawerWidthPx
            }
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .width(140.dp)
                .align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .weight(1f)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        scope.launch { draggableState.animateTo(DragValue.Closed) }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cancel")
                }
            }

            VerticalDivider(thickness = .5.dp, color = Color.White)

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.error)
                    .weight(1f)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        onDelete()
                        scope.launch { draggableState.animateTo(DragValue.Closed) }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = draggableState.requireOffset().roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal
                )
                .background(MaterialTheme.colorScheme.surface)
        ) {
            with(sharedTransitionScope) {
                ListingItemInternal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (draggableState.currentValue == DragValue.Closed) {
                                onClick()
                            }
                        }
                    ,
                    title = title,
                    body = body,
                    imageContent = { imageModifier ->
                        CoilAsyncImage(
                            modifier = imageModifier
                                .sharedElement(
                                    rememberSharedContentState(key = "image-$id"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    clipInOverlayDuringTransition = OverlayClip(CircleShape)
                                )
                                .clip(CircleShape),
                            position = id
                        )
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    id = id
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun ListingItemInternal(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    imageContent: @Composable (Modifier) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    id: Int
) = with(sharedTransitionScope) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageContent(
            Modifier
                .size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "title-$id"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    placeholderSize = SharedTransitionScope.PlaceholderSize.ContentSize
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "body-$id"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    placeholderSize = SharedTransitionScope.PlaceholderSize.ContentSize
                )
            )
        }
    }
}
