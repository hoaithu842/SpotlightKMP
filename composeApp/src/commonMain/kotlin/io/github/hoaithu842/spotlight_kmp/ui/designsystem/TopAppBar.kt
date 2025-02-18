package io.github.hoaithu842.spotlight_kmp.ui.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.extensions.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.ui.theme.MinimizedPlayerBackground
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorColor
import io.github.hoaithu842.spotlight_kmp.ui.theme.ProgressIndicatorTrackColor
import io.github.hoaithu842.spotlight_kmp.ui.theme.TopAppBarGray
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.all
import spotlight.composeapp.generated.resources.ic_launcher_background
import spotlight.composeapp.generated.resources.music
import spotlight.composeapp.generated.resources.podcasts

enum class HomeScreenTab {
    All,
    Music,
    Podcasts,
}

@Composable
fun HomeTopAppBar(
    onAvatarClick: () -> Unit,
    currentTab: HomeScreenTab,
    onTabClick: (HomeScreenTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.TopAppBarHeight)
            .horizontalScroll(rememberScrollState())
            .padding(bottom = 3.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .padding(start = SpotlightDimens.TopAppBarHorizontalPadding)
                .size(SpotlightDimens.TopAppBarOptionHeight)
                .clip(shape = CircleShape)
                .clickable { onAvatarClick() }
        )
        HomeTopAppBarOption(
            title = stringResource(Res.string.all),
            selected = currentTab == HomeScreenTab.All,
            modifier = Modifier.padding(horizontal = SpotlightDimens.TopAppBarHorizontalPadding),
            onOptionClick = {
                onTabClick(HomeScreenTab.All)
            }
        )
        HomeTopAppBarOption(
            title = stringResource(Res.string.music),
            selected = currentTab == HomeScreenTab.Music,
            modifier = Modifier.padding(horizontal = SpotlightDimens.TopAppBarHorizontalPadding),
            onOptionClick = {
                onTabClick(HomeScreenTab.Music)
            }
        )
        HomeTopAppBarOption(
            title = stringResource(Res.string.podcasts),
            selected = currentTab == HomeScreenTab.Podcasts,
            modifier = Modifier.padding(horizontal = SpotlightDimens.TopAppBarHorizontalPadding),
            onOptionClick = {
                onTabClick(HomeScreenTab.Podcasts)
            }
        )
    }
}

@Composable
fun HomeTopAppBarOption(
    title: String,
    selected: Boolean,
    onOptionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(SpotlightDimens.TopAppBarOptionHeight)
            .clip(shape = CircleShape)
            .background(if (selected) MaterialTheme.colorScheme.primary else TopAppBarGray)
            .padding(horizontal = SpotlightDimens.TopAppBarOptionPadding)
            .clickable {
                onOptionClick()
            }
    ) {
        Text(
            text = title,
            style = SpotlightTextStyle.Text11W400,
            color = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun FullsizePlayerTopAppBar(
    artists: String,
    onMinimizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.FullsizePlayerTopAppBarHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = painterResource(SpotlightIcons.Down),
            contentDescription = "",
            modifier = Modifier
                .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                .noRippleClickable {
                    onMinimizeClick()
                },
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = artists,
            style = SpotlightTextStyle.Text11W400,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = SpotlightDimens.FullsizePlayerTopAppBarPadding),
        )
        Icon(
            painter = painterResource(SpotlightIcons.More),
            contentDescription = "",
            modifier = Modifier
                .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                .noRippleClickable {},
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun PlayerControllerTopAppBar(
    songName: String,
    artists: String,
    isPlaying: Boolean,
    onPlayerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var playing by remember { mutableStateOf(isPlaying) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.MinimizedPlayerHeight)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(MinimizedPlayerBackground)
            .noRippleClickable {
                onPlayerClick()
            }
            .padding(bottom = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = SpotlightDimens.MinimizedPlayerThumbnailPaddingStart),
        ) {
            Column(
                modifier = Modifier
                    .padding(end = SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize.times(2))
                    .height(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = songName,
                    style = SpotlightTextStyle.Text11W400,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee()
                )
                Text(
                    text = artists,
                    style = SpotlightTextStyle.Text11W400,
                    overflow = TextOverflow.Ellipsis,
                    color = NavigationGray,
                    maxLines = 1,
                )
            }

            Icon(
                painter = painterResource(if (isPlaying) SpotlightIcons.Pause else SpotlightIcons.Play),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                    .align(Alignment.CenterEnd)
                    .noRippleClickable {
                        playing = !playing
                    }
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