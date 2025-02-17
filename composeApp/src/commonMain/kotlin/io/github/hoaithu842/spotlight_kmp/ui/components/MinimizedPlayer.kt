package io.github.hoaithu842.spotlight_kmp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.extensions.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.MinimizedPlayerBackground
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorColor
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorTrackColor
import org.jetbrains.compose.resources.painterResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.ic_launcher_background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinimizedPlayer(
    isPlaying: Boolean,
    songName: String,
    artists: String,
    onPlayerClick: () -> Unit,
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
                Image(
                    painter = painterResource(Res.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .size(SpotlightDimens.MinimizedPlayerThumbnailSize)
                        .clip(shape = RoundedCornerShape(size = 6.dp))
                )

                Column(
                    modifier = Modifier.padding(start = SpotlightDimens.MinimizedPlayerInfoPaddingStart)
                ) {
                    Text(
                        text = songName,
                        style = SpotlightTextStyle.Text11W400,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                    )
                    Text(
                        text = artists,
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
                        playing = !playing
                    },
            )
        }

        LinearProgressIndicator(
            progress = { 0.2f },
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