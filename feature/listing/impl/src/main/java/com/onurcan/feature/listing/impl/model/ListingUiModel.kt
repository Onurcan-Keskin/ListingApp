package com.onurcan.feature.listing.impl.model

import com.onurcan.core.model.PostModelItem

data class ListingUiModel(
    val list: List<PostModelItem> = emptyList(),
    val errorMessage: String? = null
)
