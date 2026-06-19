package com.onurcan.core.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageLoaderViewModel @Inject constructor(
    val imageLoader: ImageLoader
) : ViewModel()

@Composable
fun CoilAsyncImage(
    modifier: Modifier = Modifier,
    position: Int,
    contentScale: ContentScale = ContentScale.Crop,
    imageLoaderViewModel: ImageLoaderViewModel = hiltViewModel()
) {
    CoilAsyncImage(
        modifier = modifier,
        position = position,
        contentScale = contentScale,
        imageLoader = imageLoaderViewModel.imageLoader
    )
}

@Composable
fun CoilAsyncImage(
    modifier: Modifier = Modifier,
    position: Int,
    contentScale: ContentScale = ContentScale.Crop,
    imageLoader: ImageLoader
) {
    val context = LocalContext.current
    val model = "https://picsum.photos/300/300?random=$position&grayscale"
    Log.d("Model", model)
    val request = ImageRequest.Builder(context)
        .data(model)
        .crossfade(true)
        .build()
    SubcomposeAsyncImage(
        modifier = modifier,
        model = request,
        contentDescription = position.toString(),
        contentScale = contentScale,
        alignment = Alignment.Center,
        imageLoader = imageLoader,
        loading = {
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant))
        },
        error = {
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.errorContainer))
        }
    )
}
