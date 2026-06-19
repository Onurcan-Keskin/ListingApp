package com.onurcan.feature.listing.impl.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.onurcan.core.ui.bean.ListingItem
import com.onurcan.feature.listing.impl.event.ListingClickEvents
import com.onurcan.feature.listing.impl.model.ListingUiModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListingScreen(
    data: ListingUiModel,
    events: ((ListingClickEvents) -> Unit),
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LazyColumn {
        itemsIndexed(
            items = data.list,
            key = { index, item -> item.id ?: index }
        ) { index, item ->
            ListingItem(
                id = item.id ?: index,
                title = item.title.orEmpty(),
                body = item.body.orEmpty(),
                onClick = { events.invoke(ListingClickEvents.GoToDetail(item.id.toString())) },
                onDelete = { events.invoke(ListingClickEvents.DeletePost(item.id.toString())) },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
            if (index < data.list.lastIndex) {
                HorizontalDivider(thickness = .5.dp)
            }
        }
    }
}
