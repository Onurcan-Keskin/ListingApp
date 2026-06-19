package com.onurcan.core.domain.usecase

import com.onurcan.core.common.BaseUseCase
import com.onurcan.core.data.model.asEntity
import com.onurcan.core.data.repository.PostRepository
import com.onurcan.core.database.dao.PostModelDao
import com.onurcan.core.database.model.PostModelItemEntity
import com.onurcan.core.database.model.toExternalModel
import com.onurcan.core.domain.model.ListingDomainModel
import com.onurcan.core.model.PostModelItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.rx3.rxCompletable
import javax.inject.Inject

class ListingUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val postModelDao: PostModelDao
) : BaseUseCase<Unit, Flowable<ListingDomainModel>>() {

    fun getLocalPosts(): Flowable<List<PostModelItem>> =
        postModelDao.getAll().map { entities -> entities.map(PostModelItemEntity::toExternalModel) }

    fun syncPosts(): Completable = postRepository.getPosts()
        .flatMapCompletable { remotePosts ->
            Completable.fromAction {
                postModelDao.insertAll(remotePosts.map { item -> item.asEntity() })
            }
        }
        .onErrorComplete()

    fun deletePost(id: String): Completable =
        rxCompletable { postModelDao.deletePostModelItem(id) }

    override fun execute(request: Unit): Flowable<ListingDomainModel> = getLocalPosts().map { ListingDomainModel(it) }
}