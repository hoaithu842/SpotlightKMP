package io.github.hoaithu842.spotlight_kmp

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun getScreenWidth(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
actual fun getScreenHeight(): Dp = LocalConfiguration.current.screenHeightDp.dp