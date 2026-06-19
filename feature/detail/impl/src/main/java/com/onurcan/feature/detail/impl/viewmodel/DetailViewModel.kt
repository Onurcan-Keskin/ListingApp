package com.onurcan.feature.detail.impl.viewmodel

import com.onurcan.core.common.BaseUIModel
import com.onurcan.core.common.BaseViewModel
import com.onurcan.core.common.updateData
import com.onurcan.core.domain.usecase.DetailUseCase
import com.onurcan.feature.detail.impl.model.PostModelItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        BaseUIModel(PostModelItemUiModel())
    )
    val uiState = _uiState.asStateFlow()

    fun loadPost(id: String) {
        executeSingle(
            single = detailUseCase(id),
            uiModelFlow = _uiState,
            transform = { entity ->
                with(entity) {
                    PostModelItemUiModel(
                        body = body,
                        title = title,
                        userId = userId,
                        id = this.id
                    )
                }
            }
        )
    }

    fun updateTitle(id: String, title: String) {
        detailUseCase.updateTitle(id, title)
            .execute(
                onSuccess = {
                    _uiState.updateData { copy(title = title) }
                },
                onError = { userFriendlyErrorMessage ->
                    _uiState.updateData { copy(errorMessage = userFriendlyErrorMessage) }
                }
            )
            .also { addDisposable(it) }
    }

    fun updateBody(id: String, body: String) {
        detailUseCase.updateBody(id, body)
            .execute(
                onSuccess = {
                    _uiState.updateData { copy(body = body) }
                },
                onError = {
                    _uiState.updateData { copy(errorMessage = it) }
                }
            )
            .also { addDisposable(it) }
    }
}
