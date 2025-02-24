package io.github.hoaithu842.spotlight_kmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import io.github.hoaithu842.spotlight_kmp.manager.PlayerState

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp

expect class AudioPlayer(
    onProgressCallback: (PlayerState) -> Unit,
    onReadyCallback: () -> Unit,
    onErrorCallback: (Exception) -> Unit,
    playerState: PlayerState,
    context: Any?,
) {
    fun pause()
    fun play(url: String)
    fun playerState(): PlayerState
    fun cleanUp()
    fun seek(position: Float)
    // Flow to observe media status
}

@Composable
expect fun AudioProvider(
    audioUpdates: AudioUpdates,
    composable: @Composable (AudioPlayer) -> Unit,
)

interface AudioUpdates {
    fun onProgressUpdate(playerState: PlayerState)
    fun onReady()
    fun onError(exception: Exception)
}