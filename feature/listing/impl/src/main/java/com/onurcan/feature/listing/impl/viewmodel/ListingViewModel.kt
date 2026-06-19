package com.onurcan.feature.listing.impl.viewmodel

import com.onurcan.core.common.BaseUIModel
import com.onurcan.core.common.BaseViewModel
import com.onurcan.core.common.updateData
import com.onurcan.core.domain.usecase.ListingUseCase
import com.onurcan.feature.listing.impl.model.ListingUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val listingUseCase: ListingUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(BaseUIModel(ListingUiModel()))
    val uiState = _uiState.asStateFlow()

    init {
        observeLocalData()
        syncNetworkData()
    }

    fun deletePost(id: String) {
        listingUseCase.deletePost(id)
            .execute(
                onSuccess = {},
                onError = {
                    _uiState.updateData { copy(errorMessage = it) }
                }
            )
            .also { addDisposable(it) }
    }

    private fun observeLocalData() {
        executeFlowable(
            flowable = listingUseCase.invoke(Unit),
            uiModelFlow = _uiState,
            transform = { model -> ListingUiModel(model.list) }
        )
    }

    private fun syncNetworkData() {
        listingUseCase.syncPosts().execute(
            onSuccess = {},
            onError = {}
        )
            .also { addDisposable(it) }
    }
}