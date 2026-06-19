package com.onurcan.core.database.di

import com.onurcan.core.database.ListingAppDatabase
import com.onurcan.core.database.dao.PostModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesTopicsDao(
        database: ListingAppDatabase,
    ): PostModelDao = database.postModelDao()
}