package com.onurcan.core.data.service

import com.onurcan.core.model.PostModelResource
import com.onurcan.core.model.PostModelItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    fun getPost(): Single<PostModelResource>
}
