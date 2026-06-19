package com.onurcan.feature.listing.impl.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.onurcan.core.navigation.Navigator
import com.onurcan.feature.detail.api.navigation.navigateToDetail
import com.onurcan.feature.listing.api.navigation.ListingNavKey

@OptIn(ExperimentalSharedTransitionApi::class)
fun EntryProviderScope<NavKey>.listingEntry(
    navigator: Navigator,
    sharedTransitionScope: SharedTransitionScope
) {
    entry<ListingNavKey> {
        ListingNavigation(
            onTopicClick = navigator::navigateToDetail,
            sharedTransitionScope = sharedTransitionScope,
        )
    }
}
