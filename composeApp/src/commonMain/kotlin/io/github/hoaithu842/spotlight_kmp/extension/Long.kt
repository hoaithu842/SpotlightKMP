package io.github.hoaithu842.spotlight_kmp.extension

import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Long.toTimeFormat(): String {
    if (this <= 0) return "0:00"

    val duration = toDuration(DurationUnit.MILLISECONDS)
    val mins = duration.inWholeMinutes
    val secs = duration.minus(mins.toDuration(DurationUnit.MINUTES)).inWholeSeconds

    return "$mins:${secs.toString().padStart(length = 2, padChar = '0')}"
}