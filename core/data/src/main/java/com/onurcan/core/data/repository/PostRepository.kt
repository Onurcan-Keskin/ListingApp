package com.onurcan.core.data.repository

import com.onurcan.core.common.BaseRepository
import com.onurcan.core.data.service.PostService
import io.reactivex.rxjava3.core.Single
import com.onurcan.core.model.PostModelResource
import com.onurcan.core.model.PostModelItem

class PostRepository(
    private val postService: PostService,
): BaseRepository() {
    fun getPosts(): Single<PostModelResource> = sendRequest {
        postService.getPost()
    }
}
