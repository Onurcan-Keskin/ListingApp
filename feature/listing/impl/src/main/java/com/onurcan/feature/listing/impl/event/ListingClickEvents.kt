package com.onurcan.feature.listing.impl.event

sealed interface ListingClickEvents {
    data class GoToDetail(val id: String) : ListingClickEvents
    data class DeletePost(val id: String) : ListingClickEvents
}