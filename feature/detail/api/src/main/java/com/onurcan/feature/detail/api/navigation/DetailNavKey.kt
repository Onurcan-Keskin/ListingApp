package com.onurcan.feature.detail.api.navigation

import androidx.navigation3.runtime.NavKey
import com.onurcan.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
data class DetailNavKey(val id: String) : NavKey {

}

fun Navigator.navigateToDetail(
    detailId: String
) {
    navigate(DetailNavKey(detailId))
}