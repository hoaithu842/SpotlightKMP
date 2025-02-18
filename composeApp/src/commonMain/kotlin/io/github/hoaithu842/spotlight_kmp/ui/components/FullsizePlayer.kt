package io.github.hoaithu842.spotlight_kmp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.extensions.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.FullsizePlayerTopAppBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.PlayerControllerTopAppBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.MinimizedPlayerBackground
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorColor
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorTrackColor
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.ic_launcher_background

@Composable
fun FullsizePlayer(
    songName: String,
    artists: String,
    onMinimizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val shouldDisplayTopAppBar by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 1
        }
    }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .background(MinimizedPlayerBackground)
            .statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            item {
                FullsizePlayerTopAppBar(
                    artists = "Đen",
                    onMinimizeClick = onMinimizeClick,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = SpotlightDimens.FullsizePlayerTopAppBarPadding)
                )
            }
            item {
                MainPlayerContent(
                    songName = songName,
                    artists = artists,
                )
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                    Text(
                        text = "Some text",
                        modifier = Modifier.padding(vertical = SpotlightDimens.MinimizedPlayerHeight)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = shouldDisplayTopAppBar,
            enter = slideInVertically(),
            exit = fadeOut(),
        ) {
            PlayerControllerTopAppBar(
                songName = songName,
                artists = artists,
                isPlaying = true,
                onPlayerClick = {
                    scope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            )
        }
    }
}

@Composable
fun MainPlayerContent(
    songName: String,
    artists: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = SpotlightDimens.FullsizePlayerMainContentHorizontalPadding)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = SpotlightDimens.FullsizePlayerMainContentPadding)
                .size(SpotlightDimens.FullsizePlayerThumbnailSize),
            contentScale = ContentScale.Crop, // TODO: replace later
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(end = SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize.times(2))
            ) {
                Text(
                    text = songName,
                    style = SpotlightTextStyle.Text22W400,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee()
                )
                Text(
                    text = artists,
                    style = SpotlightTextStyle.Text16W400,
                    overflow = TextOverflow.Ellipsis,
                    color = NavigationGray,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Icon(
                painter = painterResource(SpotlightIcons.Heart),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                    .align(Alignment.CenterEnd)
                    .noRippleClickable { }
            )
        }

        LinearProgressIndicator(
            progress = { 0.2f },
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            color = ProgressIndicatorTrackColor,
            trackColor = ProgressIndicatorColor,
            strokeCap = StrokeCap.Round,
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "0:00",
                style = SpotlightTextStyle.Text11W400,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "-1:18",
                style = SpotlightTextStyle.Text11W400,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        PlayerController(
            modifier = Modifier.padding(vertical = 14.dp)
        )
    }
}

@Composable
fun PlayerController(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = painterResource(SpotlightIcons.Shuffle),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(SpotlightDimens.PlayerControllerSmallIconSize)
                .noRippleClickable { },
        )
        Icon(
            painter = painterResource(SpotlightIcons.PlayPrevious),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(SpotlightDimens.PlayerControllerMediumIconSize)
                .noRippleClickable { },
        )
        Icon(
            painter = painterResource(SpotlightIcons.Pause),
            contentDescription = "",
            tint = MinimizedPlayerBackground,
            modifier = Modifier
                .size(SpotlightDimens.PlayerControllerLargeIconSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground)
                .padding((SpotlightDimens.PlayerControllerLargeIconSize - SpotlightDimens.PlayerControllerMediumIconSize) / 2)
                .noRippleClickable { },
        )
        Icon(
            painter = painterResource(SpotlightIcons.PlayNext),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(SpotlightDimens.PlayerControllerMediumIconSize)
                .noRippleClickable { },
        )
        Icon(
            painter = painterResource(SpotlightIcons.Timer),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(SpotlightDimens.PlayerControllerSmallIconSize)
                .noRippleClickable { },
        )
    }
}