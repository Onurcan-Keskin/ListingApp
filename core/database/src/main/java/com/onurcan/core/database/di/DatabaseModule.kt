package com.onurcan.core.database.di

import android.content.Context
import androidx.room.Room
import com.onurcan.core.database.ListingAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesListingDatabase(
        @ApplicationContext context: Context,
    ): ListingAppDatabase = Room.databaseBuilder(
        context,
        ListingAppDatabase::class.java,
        "listing-database",
    ).build()
}