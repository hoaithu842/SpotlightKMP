package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import io.github.hoaithu842.spotlight_kmp.extension.shimmerLoadingAnimation

@Composable
fun ArtistThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        ),
        loading = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme(),
                    )
            )
        },
        failure = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
    )
}

@Composable
fun EPThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        ),
        loading = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme(),
                    )
            )
        },
        failure = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
    )
}

@Composable
fun PlaylistThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        ),
        loading = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme(),
                    )
            )
        },
        failure = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
    )
}

@Composable
fun LibraryPlaylistThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        ),
        loading = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme(),
                    )
            )
        },
        failure = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
            }
        },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun SongThumbnail(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        ),
        loading = {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(size = 6.dp))
                    .shimmerLoadingAnimation(
                        isLoadingCompleted = false,
                        isLightModeActive = !isSystemInDarkTheme(),
                    )
            )
        },
        failure = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(size = 6.dp))
    )
}