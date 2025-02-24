package io.github.hoaithu842.spotlight_kmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.manager.PlayerState
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.MutableStateFlow
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.AVPlayerTimeControlStatusPlaying
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.AVFoundation.currentItem
import platform.AVFoundation.duration
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.removeTimeObserver
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.AVFoundation.timeControlStatus
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeCompare
import platform.CoreMedia.CMTimeGetSeconds
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import platform.UIKit.UIDevice
import platform.UIKit.UIScreen
import platform.darwin.NSEC_PER_SEC
import platform.darwin.NSObject
import platform.darwin.NSObjectProtocol

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidth(): Dp = LocalWindowInfo.current.containerSize.width.pxToPoint().dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight(): Dp = LocalWindowInfo.current.containerSize.height.pxToPoint().dp

fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale

actual class AudioPlayer actual constructor(
    private val onProgressCallback: (PlayerState) -> Unit,
    private val onReadyCallback: () -> Unit,
    private val onErrorCallback: (Exception) -> Unit,
    playerState: PlayerState,
    context: Any?
) : NSObject() {
    private val avAudioPlayer: AVPlayer = AVPlayer()

    private var currentPlayingResource: String? = null

    private lateinit var timeObserver: Any

    private var playbackEndObserver: NSObjectProtocol? = null

    private val _playerState = MutableStateFlow(PlayerState())

    @OptIn(ExperimentalForeignApi::class)
    private val observer: (CValue<CMTime>) -> Unit = { time: CValue<CMTime> ->
        // Retrieve the current playback time in seconds.
        val currentTimeInSeconds = CMTimeGetSeconds(time)
        // Retrieve the total duration of the current media item.
        val totalDurationInSeconds =
            avAudioPlayer.currentItem?.duration?.let { CMTimeGetSeconds(it) } ?: Double.NaN

        // Only update if valid values are present.
        if (!currentTimeInSeconds.isNaN() &&
            !totalDurationInSeconds.isNaN() &&
            totalDurationInSeconds > 0
        ) {
            _playerState.value = _playerState.value.copy(
                currentTime = currentTimeInSeconds.toFloat(),
                duration = totalDurationInSeconds.toFloat(),
                isPlaying = _playerState.value.isPlaying,
                isBuffering = _playerState.value.isBuffering,
                currentPlayingResource = currentPlayingResource
            )
            onProgressCallback(_playerState.value)
        } else {
            println("Skipping progress update due to invalid time values.")
        }
    }

    init {
        // Configure the audio session for playback.
        setUpAudioSession()
        // Initialize the player state based on the AVPlayer's status.
        _playerState.value = _playerState.value.copy(
            isPlaying = (avAudioPlayer.timeControlStatus == AVPlayerTimeControlStatusPlaying)
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun play(url: String) {
        // Check if we're trying to resume the same resource.
        if (currentPlayingResource == url && avAudioPlayer.currentItem != null &&
            avAudioPlayer.timeControlStatus != AVPlayerTimeControlStatusPlaying
        ) {
            // Resume playback without restarting.
            avAudioPlayer.play()
            _playerState.value = _playerState.value.copy(
                isPlaying = true,
                isBuffering = false
            )
            onProgressCallback(_playerState.value)
            return
        }

        // For a new resource, stop the previous playback.
        currentPlayingResource = url
        _playerState.value = _playerState.value.copy(
            isBuffering = true,
            currentPlayingResource = url
        )
        stop()
        startTimeObserver()

        try {
            val nsUrl = NSURL.URLWithString(url) ?: throw IllegalArgumentException("Invalid URL")
            val playItem = AVPlayerItem(uRL = nsUrl)

            if (CMTimeCompare(
                    playItem.duration,
                    CMTimeMakeWithSeconds(0.0, NSEC_PER_SEC.toInt())
                ) == 0
            ) {
                throw IllegalStateException("Invalid media duration.")
            }

            avAudioPlayer.replaceCurrentItemWithPlayerItem(playItem)
            avAudioPlayer.play()
            _playerState.value = _playerState.value.copy(
                isPlaying = true,
                isBuffering = false
            )
            onReadyCallback()
        } catch (e: Exception) {
            onErrorCallback(e)
            if (::timeObserver.isInitialized) {
                avAudioPlayer.removeTimeObserver(timeObserver)
            }
        }
    }

    actual fun pause() {
        avAudioPlayer.pause()
        _playerState.value = _playerState.value.copy(
            isPlaying = false,
            isBuffering = false,
            currentPlayingResource = currentPlayingResource
        )
        onProgressCallback(_playerState.value)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setUpAudioSession() {
        try {
            val audioSession = AVAudioSession.sharedInstance()
            audioSession.setCategory(AVAudioSessionCategoryPlayback, null)
            audioSession.setActive(true, null)
        } catch (e: Exception) {
            println("Error setting up audio session: ${e.message}")
            onErrorCallback(e)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun startTimeObserver() {
        val interval = CMTimeMakeWithSeconds(0.1, NSEC_PER_SEC.toInt())
        timeObserver = avAudioPlayer.addPeriodicTimeObserverForInterval(interval, null, observer)

        playbackEndObserver = NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = avAudioPlayer.currentItem,
            queue = NSOperationQueue.mainQueue,
            usingBlock = { _ ->
                _playerState.value = _playerState.value.copy(
                    isPlaying = false,
                    isBuffering = false,
                    currentPlayingResource = currentPlayingResource
                )
                onProgressCallback(_playerState.value)
            }
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun stop() {
        // Remove the time observer if it was set.
        if (::timeObserver.isInitialized) {
            avAudioPlayer.removeTimeObserver(timeObserver)
        }
        // Remove the playback end observer.
        playbackEndObserver?.let {
            NSNotificationCenter.defaultCenter.removeObserver(it)
            playbackEndObserver = null
        }

        avAudioPlayer.pause()
        _playerState.value = _playerState.value.copy(
            isPlaying = false,
            isBuffering = false,
            currentTime = 0f,
            currentPlayingResource = null
        )
        onProgressCallback(_playerState.value)

        // Seek to the beginning of the current item.
        avAudioPlayer.currentItem?.seekToTime(CMTimeMakeWithSeconds(0.0, NSEC_PER_SEC.toInt()))
    }

    actual fun cleanUp() {
        stop()
    }

    actual fun playerState(): PlayerState {
        return _playerState.value
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun seek(position: Float) {
        val time = CMTimeMakeWithSeconds(position.toDouble(), NSEC_PER_SEC.toInt())
        avAudioPlayer.seekToTime(time)
    }
}

@Composable
actual fun AudioProvider(
    audioUpdates: AudioUpdates,
    composable: @Composable (AudioPlayer) -> Unit
) {
    val audioPlayer =
        AudioPlayer(
            onProgressCallback = {
                audioUpdates.onProgressUpdate(it)
            },
            context = null, onReadyCallback = {
                audioUpdates.onReady()
            },
            onErrorCallback = {
                audioUpdates.onError(it)
            },
            playerState = PlayerState()
        )
    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.cleanUp()
        }
    }
    composable(audioPlayer)
}