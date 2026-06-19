package com.onurcan.feature.detail.impl.events

sealed interface DetailClickEvents {
    data class UpdateTitle(val title: String) : DetailClickEvents
    data class UpdateBody(val body: String) : DetailClickEvents
}