package com.onurcan.core.common

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseRepository {
    fun <T : Any> sendRequest(call: () -> Single<T>): Single<T> =
        call.invoke()
            .subscribeOn(Schedulers.io())
}
