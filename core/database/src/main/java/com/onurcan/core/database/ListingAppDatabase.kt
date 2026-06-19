package com.onurcan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurcan.core.database.dao.PostModelDao
import com.onurcan.core.database.model.PostModelItemEntity

@Database(
    entities = [
        PostModelItemEntity::class
    ],
    version = 1,
    exportSchema = true,
)
internal abstract class ListingAppDatabase : RoomDatabase() {
    abstract fun postModelDao(): PostModelDao
}