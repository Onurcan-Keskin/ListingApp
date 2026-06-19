package com.onurcan.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onurcan.core.database.model.PostModelItemEntity
import com.onurcan.core.model.PostModelItem
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PostModelDao {
    @Query("SELECT * FROM post_model_item_table")
    fun getAll(): Flowable<List<PostModelItemEntity>>

    @Query("SELECT * FROM post_model_item_table WHERE id = :id")
    fun getPostModelItem(id: String): Flowable<PostModelItemEntity>

    @Query("UPDATE post_model_item_table SET title = :title WHERE id = :id")
    fun updateTitle(id: String, title: String)

    @Query("UPDATE post_model_item_table SET body = :body WHERE id = :id")
    fun updateBody(id: String, body: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(posts: List<PostModelItemEntity>)

    @Query("DELETE FROM post_model_item_table WHERE id = :id")
    fun deletePostModelItem(id: String)
}