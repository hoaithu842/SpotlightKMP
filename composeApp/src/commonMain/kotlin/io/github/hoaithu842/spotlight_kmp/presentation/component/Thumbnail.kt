package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import io.github.hoaithu842.spotlight_kmp.extension.shimmerLoadingAnimation

@Composable
fun ArtistThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    var isLoading by remember { mutableStateOf(true) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .listener(onSuccess = { _, _ ->
                isLoading = false
            })
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
            .shimmerLoadingAnimation(
                isLoadingCompleted = !isLoading,
                isLightModeActive = !isSystemInDarkTheme(),
            )
    )
}

@Composable
fun EPThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    var isLoading by remember { mutableStateOf(true) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .listener(onSuccess = { _, _ ->
                isLoading = false
            })
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .shimmerLoadingAnimation(
                isLoadingCompleted = !isLoading,
                isLightModeActive = !isSystemInDarkTheme(),
            )
    )
}

@Composable
fun PlaylistThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    var isLoading by remember { mutableStateOf(true) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .listener(onSuccess = { _, _ ->
                isLoading = false
            })
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .shimmerLoadingAnimation(
                isLoadingCompleted = !isLoading,
                isLightModeActive = !isSystemInDarkTheme(),
            )
    )
}

@Composable
fun SongThumbnail(
    painter: AsyncImagePainter,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
    )
}