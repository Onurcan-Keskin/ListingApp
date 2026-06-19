package com.onurcan.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onurcan.core.model.PostModelItem

@Entity(
    tableName = "post_model_item_table"
)
data class PostModelItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "userId")
    val userId: Int
)

fun PostModelItemEntity.toExternalModel(): PostModelItem = PostModelItem(
    id = id,
    body = body,
    title = title,
    userId = userId
)