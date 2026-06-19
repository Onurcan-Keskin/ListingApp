package com.onurcan.core.domain.di

import com.onurcan.core.data.repository.PostRepository
import com.onurcan.core.database.dao.PostModelDao
import com.onurcan.core.domain.usecase.DetailUseCase
import com.onurcan.core.domain.usecase.ListingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModelProvider {

    @Provides
    @Singleton
    fun provideListingUseCase(
        postRepository: PostRepository,
        postModelDao: PostModelDao
    ): ListingUseCase = ListingUseCase(postRepository, postModelDao)

    @Provides
    @Singleton
    fun provideDetailUseCase(
        postModelDao: PostModelDao
    ): DetailUseCase = DetailUseCase(postModelDao)

}