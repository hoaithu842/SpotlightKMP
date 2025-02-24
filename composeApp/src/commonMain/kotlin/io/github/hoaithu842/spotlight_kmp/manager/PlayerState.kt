package io.github.hoaithu842.spotlight_kmp.manager

data class PlayerState(
    var isPlaying: Boolean = false,
    var isBuffering: Boolean = false,
    var currentTime: Float = 0f,
    var duration: Float = 0f,
    var currentPlayingResource: String? = null

) {
    val progress = currentTime.toFloat() / duration.toFloat()
}