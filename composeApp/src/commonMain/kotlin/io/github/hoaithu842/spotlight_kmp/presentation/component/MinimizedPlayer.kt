package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import io.github.hoaithu842.spotlight_kmp.domain.model.Song
import io.github.hoaithu842.spotlight_kmp.extension.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.extension.shimmerLoadingAnimation
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.MinimizedPlayerBackground
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorColor
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorTrackColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun MinimizedPlayer(
    isPlaying: Boolean,
    song: Song,
    currentPosition: Float,
    duration: Float,
    onPlayerClick: () -> Unit,
    onMainFunctionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var playing by remember { mutableStateOf(isPlaying) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.MinimizedPlayerHeight)
            .clip(
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(MinimizedPlayerBackground)
            .clickable {
                onPlayerClick()
            }
            .padding(bottom = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpotlightDimens.MinimizedPlayerThumbnailPaddingStart),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize.times(2)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CoilImage(
                    imageModel = { song.image },
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
                    modifier = Modifier
                        .size(SpotlightDimens.MinimizedPlayerThumbnailSize)
                        .clip(shape = RoundedCornerShape(size = 6.dp))
                )

                Column(
                    modifier = Modifier.padding(start = SpotlightDimens.MinimizedPlayerInfoPaddingStart)
                ) {
                    Text(
                        text = song.title,
                        style = SpotlightTextStyle.Text11W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .basicMarquee()
                    )
                    Text(
                        text = song.artists,
                        style = SpotlightTextStyle.Text11W400,
                        overflow = TextOverflow.Ellipsis,
                        color = NavigationGray,
                        maxLines = 1,
                    )
                }
            }

            Icon(
                painter = painterResource(if (playing) SpotlightIcons.Pause else SpotlightIcons.Play),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                    .align(Alignment.CenterEnd)
                    .noRippleClickable {
                        onMainFunctionClick()
                    },
            )
        }

        LinearProgressIndicator(
            progress = { if (duration.toInt() == 0) 0f else (currentPosition * 1.0 / duration).toFloat() },
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.MinimizedPlayerProgressIndicatorPadding)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = ProgressIndicatorTrackColor,
            trackColor = ProgressIndicatorColor,
            strokeCap = StrokeCap.Round,
        )
    }
}