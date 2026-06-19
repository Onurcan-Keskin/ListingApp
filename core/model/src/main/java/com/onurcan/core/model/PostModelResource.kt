package com.onurcan.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Post model resource are fetched from the network
 */
class PostModelResource : ArrayList<PostModelItem>()

@Serializable
data class PostModelItem(
    @SerialName("userId")
    val userId: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("body")
    val body: String?
)