package io.github.hoaithu842.spotlight_kmp.extension

fun Float.toTimeFormat(): String {
    val totalSeconds = this.toInt() // Use the whole number part as seconds
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "${minutes}:${seconds.toString().padStart(length = 2, padChar = '0')}"
}