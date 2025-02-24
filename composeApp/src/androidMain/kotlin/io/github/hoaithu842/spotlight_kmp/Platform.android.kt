package io.github.hoaithu842.spotlight_kmp

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import io.github.hoaithu842.spotlight_kmp.manager.PlayerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun getScreenWidth(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
actual fun getScreenHeight(): Dp = LocalConfiguration.current.screenHeightDp.dp

actual class AudioPlayer actual constructor(
    private val onProgressCallback: (PlayerState) -> Unit,
    onReadyCallback: () -> Unit,
    onErrorCallback: (Exception) -> Unit,
    playerState: PlayerState,
    context: Any?
) {
    private val androidContext: Context = when (context) {
        is Context -> context
        else -> throw IllegalArgumentException("Expected a valid Android Context for 'context' parameter.")
    }

    private var mediaPlayer: ExoPlayer = ExoPlayer.Builder(androidContext).build()

    private val mediaItems = mutableListOf<MediaItem>()

    private var currentItemIndex = -1

    private val _playerState = MutableStateFlow(PlayerState())

    // Expose the player state flow as a read-only state flow for external subscribers.
    private val playerState = _playerState.asStateFlow()

    // A reference to the file or URL currently being played.
    private var currentPlayingResource: String? = null

    // A Job that handles continuous progress updates while audio is playing.
    private var progressJob: Job? = null

    // A scope for launching coroutines related to the player.
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    // ExoPlayer event listener to handle state changes and errors.
    private val listener = object : Player.Listener {

        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_IDLE -> {
                    // Player is idle (before prepare or after release).
                }

                Player.STATE_BUFFERING -> {
                    // Update our state to reflect buffering.
                    _playerState.update { it.copy(isBuffering = true) }
                    updateMediaStatus()
                }

                Player.STATE_READY -> {
                    // Player is ready to play. Notify that we're set.
                    onReadyCallback()

                    // Only set duration if the player actually knows it.
                    val durationMs = mediaPlayer.duration
                    val durationSec = if (durationMs != C.TIME_UNSET) durationMs / 1000f else 0f

                    _playerState.update {
                        it.copy(
                            isBuffering = false,
                            duration = durationSec
                        )
                    }
                    updateMediaStatus()
                }

                Player.STATE_ENDED -> {
                    // Playback ended. You could trigger a callback here if needed.
                    stopProgressUpdates()
                }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _playerState.update { it.copy(isPlaying = isPlaying) }
            updateMediaStatus()

            // Manage the job that updates progress in real time.
            if (isPlaying) {
                startProgressUpdates()
            } else {
                stopProgressUpdates()
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            // Reset buffering flag on error, then notify via callback.
            _playerState.update { it.copy(isBuffering = false) }
            onErrorCallback(error)
        }
    }

    init {
        // Attach the listener to the ExoPlayer instance.
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .build()

        mediaPlayer.setAudioAttributes(audioAttributes, true)
        mediaPlayer.addListener(listener)
    }

    actual fun play(url: String) {
        currentPlayingResource = url

        // Use Uri.fromFile(...) if it's truly a local file, otherwise parse as a normal URI.
        val mediaItem = if (isLocalFile(url)) {
            MediaItem.fromUri(Uri.fromFile(File(url)))
        } else {
            MediaItem.fromUri(Uri.parse(url))
        }

        // Clear previously set media items if necessary, then set the new one.
        mediaPlayer.setMediaItem(mediaItem)
        mediaPlayer.prepare()
        mediaPlayer.play()

        // Duration may still be 0 until the player is fully ready (handled in the listener).
        _playerState.update {
            it.copy(
                currentPlayingResource = url
            )
        }
        updateMediaStatus()
    }

    actual fun pause() {
        mediaPlayer.pause()
        _playerState.update { it.copy(isPlaying = false) }
        updateMediaStatus()
    }

    actual fun cleanUp() {
        mediaPlayer.stop()
        mediaPlayer.release()
        mediaPlayer.removeListener(listener)
        currentPlayingResource = null
        stopProgressUpdates()
    }

    private fun startProgressUpdates() {
        stopProgressUpdates() // Ensure we don't create multiple jobs.
        progressJob = coroutineScope.launch {
            while (_playerState.value.isPlaying) {
                val currentPos = mediaPlayer.currentPosition.toFloat()
                val totalDuration = mediaPlayer.duration.takeIf { it != C.TIME_UNSET } ?: 1L
                val progressPercentage = currentPos / totalDuration

                _playerState.update {
                    it.copy(currentTime = currentPos / 1000f)
                }


                onProgressCallback(_playerState.value)
                delay(100) // ~10 updates per second
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun isLocalFile(path: String): Boolean {
        val file = File(path)
        return file.exists() && file.isFile
    }

    private fun updateMediaStatus() {
        val currentPosSec = mediaPlayer.currentPosition / 1000f
        _playerState.update { it.copy(currentTime = currentPosSec) }
        onProgressCallback(_playerState.value)
    }

    actual fun seek(position: Float) {
        mediaPlayer.seekTo((position * 1000).toLong())
    }

    actual fun playerState(): PlayerState {
        return playerState.value
    }
}

@Composable
actual fun AudioProvider(
    audioUpdates: AudioUpdates,
    composable: @Composable (AudioPlayer) -> Unit
) {
    val context = LocalContext.current

    val audioPlayer = remember {
        AudioPlayer(
            onProgressCallback = { audioUpdates.onProgressUpdate(it) },
            context = context,
            onReadyCallback = { audioUpdates.onReady() },
            onErrorCallback = { audioUpdates.onError(it) },
            playerState = PlayerState(),
        )
    }

    DisposableEffect(audioPlayer) {
        onDispose {
            audioPlayer.cleanUp()
        }
    }

    composable(audioPlayer)
}