package com.onurcan.feature.detail.impl.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.onurcan.core.ui.CoilAsyncImage
import com.onurcan.core.ui.bean.SavableEditBox
import com.onurcan.core.ui.extensions.drawBlackGradientToBottom
import com.onurcan.feature.detail.impl.events.DetailClickEvents
import com.onurcan.feature.detail.impl.model.PostModelItemUiModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    post: PostModelItemUiModel,
    events: ((DetailClickEvents) -> Unit),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) = with(sharedTransitionScope) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .drawBlackGradientToBottom(.7f)
        ) {
            CoilAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .sharedElement(
                        rememberSharedContentState(key = "image-${post.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        clipInOverlayDuringTransition = OverlayClip(RectangleShape)
                    ),
                position = post.id ?: 0,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                    .padding(16.dp)
            ) {
                SavableEditBox(
                    modifier = Modifier.sharedElement(
                        rememberSharedContentState(key = "title-${post.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        placeholderSize = SharedTransitionScope.PlaceholderSize.ContentSize
                    ),
                    currentText = post.title.orEmpty(),
                    isTitle = true
                ) {
                    events.invoke(DetailClickEvents.UpdateTitle(it))
                }
                Spacer(modifier = Modifier.height(16.dp))
                SavableEditBox(
                    modifier = Modifier.sharedElement(
                        rememberSharedContentState(key = "body-${post.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        placeholderSize = SharedTransitionScope.PlaceholderSize.ContentSize
                    ),
                    currentText = post.body.orEmpty()
                ) {
                    events.invoke(DetailClickEvents.UpdateBody(it))
                }
            }
        }
    }
}


