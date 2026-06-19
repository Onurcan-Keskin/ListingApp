package com.onurcan.core.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class BaseUIModel<T>(val data: T, var status: UiStatus<T> = UiStatus.Initial) {
    fun setInitial() = this.copy(status = UiStatus.Initial)
    fun setLoading() = this.copy(status = UiStatus.Loading)
    fun setSuccess(data: T): BaseUIModel<T> = this.copy(data = data, status = UiStatus.Success)
    fun setError(code: String? = null, message: String? = null): BaseUIModel<T> =
        this.copy(status = UiStatus.Error(code, message))
}

fun <T> MutableStateFlow<BaseUIModel<T>>.updateData(newValue: T.() -> T) =
    update { it.copy(data = it.data.newValue()) }
fun <T> MutableStateFlow<BaseUIModel<T>>.setInitial() = update { it.setInitial() }
fun <T> MutableStateFlow<BaseUIModel<T>>.setLoading() = update { it.setLoading() }
fun <T> MutableStateFlow<BaseUIModel<T>>.setSuccess(data: T) = update { it.setSuccess(data) }
fun <T> MutableStateFlow<BaseUIModel<T>>.setError(code: String? = null, message: String? = null) =
    update { it.setError(code, message) }