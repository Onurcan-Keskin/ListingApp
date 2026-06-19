package com.onurcan.core.common

sealed interface UiStatus<out T> {
    data object Initial : UiStatus<Nothing>
    data object Loading : UiStatus<Nothing>
    data object Success : UiStatus<Nothing>
    data class Error(val code: String? = null, val message: String? = null) : UiStatus<Nothing>
}