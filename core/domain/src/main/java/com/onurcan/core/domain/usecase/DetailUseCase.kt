package com.onurcan.core.domain.usecase

import com.onurcan.core.common.BaseUseCase
import com.onurcan.core.database.dao.PostModelDao
import com.onurcan.core.database.model.PostModelItemEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.rx3.rxCompletable

class DetailUseCase(
    private val postModelDao: PostModelDao
) : BaseUseCase<String, Single<PostModelItemEntity>>() {

    private fun getPost(id: String): Single<PostModelItemEntity> =
        postModelDao.getPostModelItem(id).firstOrError()

    fun updateTitle(id: String, title: String): Completable =
        rxCompletable {
            postModelDao.updateTitle(id, title)
        }

    fun updateBody(id: String, body: String): Completable =
        rxCompletable { postModelDao.updateBody(id, body) }

    override fun execute(request: String): Single<PostModelItemEntity> = getPost(request)
}