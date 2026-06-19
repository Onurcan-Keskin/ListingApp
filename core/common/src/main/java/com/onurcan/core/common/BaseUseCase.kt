package com.onurcan.core.common

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

abstract class BaseUseCase<in P, R : Any> {
    protected abstract fun execute(request: P): R
    operator fun invoke(request: P): R = execute(request)
}
