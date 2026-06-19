package com.onurcan.core.data.model

import com.onurcan.core.database.model.PostModelItemEntity
import com.onurcan.core.model.PostModelItem

fun PostModelItem.asEntity() = PostModelItemEntity(
    id = id ?: 0,
    body = body.orEmpty(),
    title = title.orEmpty(),
    userId = userId ?: 0
)