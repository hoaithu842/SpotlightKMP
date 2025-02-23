package io.github.hoaithu842.spotlight_kmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp