package com.onurcan.core.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected fun addDisposable(d: Disposable) {
        disposable.add(d)
    }

    fun Completable.execute(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ): Disposable = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { onSuccess() },
            {
                val message = mapThrowableToMessage(it)
                onError(message) }
        )

    private fun mapThrowableToMessage(throwable: Throwable): String {
        return when (throwable) {
            is java.io.IOException -> "Network issue. Please check your connection."
            is android.database.sqlite.SQLiteFullException -> "Your device storage is completely full."
            else -> "Something went wrong. Please try again later."
        }
    }

    fun <DomainModel : Any, UiModel : Any> executeSingle(
        single: Single<DomainModel>,
        uiModelFlow: MutableStateFlow<BaseUIModel<UiModel>>,
        transform: (DomainModel) -> UiModel,
    ): Disposable = single
        .doOnSubscribe { uiModelFlow.setLoading() }
        .map { transform(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                uiModelFlow.setSuccess(it)
            },
            {
                uiModelFlow.setError(message = it.message)
            }
        ).also { addDisposable(it) }

    fun <DomainModel : Any, UiModel : Any> executeFlowable(
        flowable: Flowable<DomainModel>,
        uiModelFlow: MutableStateFlow<BaseUIModel<UiModel>>,
        transform: (DomainModel) -> UiModel,
    ): Disposable = flowable
        .doOnSubscribe { uiModelFlow.setLoading() }
        .map { transform(it) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                uiModelFlow.setSuccess(it)
            },
            {
                uiModelFlow.setError(message = it.message)
            }
        ).also { addDisposable(it) }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
