package io.github.hoaithu842.spotlight_kmp.presentation.designsystem

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.wrapContentSize
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
import io.github.hoaithu842.spotlight_kmp.domain.model.Song
import io.github.hoaithu842.spotlight_kmp.extension.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightDimens.TopAppBarHorizontalPadding
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
import spotlight.composeapp.generated.resources.library
import spotlight.composeapp.generated.resources.music
import spotlight.composeapp.generated.resources.podcasts
import spotlight.composeapp.generated.resources.search

enum class HomeScreenTab {
    All,
    Music,
    Podcasts,
}

enum class FilterCategory {
    None,
    Playlists,
    Podcasts,
    Albums,
    Artists,
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
            .fillMaxSize()
            .padding(bottom = TopAppBarHorizontalPadding * 2),
        verticalAlignment = Alignment.Bottom,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        start = SpotlightDimens.TopAppBarIconHorizontalPadding * 2,
                        end = SpotlightDimens.TopAppBarIconHorizontalPadding
                    )
                    .size(SpotlightDimens.TopAppBarIconSize)
                    .clip(shape = CircleShape)
                    .clickable { onAvatarClick() }
            )
            HomeTopAppBarOption(
                title = stringResource(Res.string.all),
                selected = currentTab == HomeScreenTab.All,
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
                onOptionClick = {
                    onTabClick(HomeScreenTab.All)
                }
            )
            HomeTopAppBarOption(
                title = stringResource(Res.string.music),
                selected = currentTab == HomeScreenTab.Music,
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
                onOptionClick = {
                    onTabClick(HomeScreenTab.Music)
                }
            )
            HomeTopAppBarOption(
                title = stringResource(Res.string.podcasts),
                selected = currentTab == HomeScreenTab.Podcasts,
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
                onOptionClick = {
                    onTabClick(HomeScreenTab.Podcasts)
                }
            )
        }
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
fun LibraryTopAppBar(
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = TopAppBarHorizontalPadding * 2),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.TopAppBarIconHorizontalPadding * 2)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                Image(
                    painter = painterResource(Res.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = SpotlightDimens.TopAppBarIconHorizontalPadding)
                        .size(SpotlightDimens.TopAppBarIconSize)
                        .clip(shape = CircleShape)
                        .clickable { onAvatarClick() }
                )
                Text(
                    text = stringResource(Res.string.library),
                    style = SpotlightTextStyle.Text22W700,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Row {
                Icon(
                    painter = painterResource(SpotlightIcons.Search),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = TopAppBarHorizontalPadding)
                        .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                        .padding(1.dp),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
                Icon(
                    painter = painterResource(SpotlightIcons.Add_2),
                    contentDescription = "",
                    modifier = Modifier.size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
        LibraryFiltering(
            modifier = Modifier.padding(horizontal = SpotlightDimens.TopAppBarIconHorizontalPadding * 2)
        )
    }
}

@Composable
fun LibraryFiltering(
    modifier: Modifier = Modifier,
) {
    var isFiltered by remember { mutableStateOf(false) }
    var filterCategory by remember { mutableStateOf(FilterCategory.None) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.TopAppBarHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(
            visible = isFiltered
        ) {
            Icon(
                painter = painterResource(SpotlightIcons.Add),
                contentDescription = "",
                modifier = Modifier
                    .size(SpotlightDimens.TopAppBarOptionHeight)
                    .noRippleClickable {
                        isFiltered = false
                        filterCategory = FilterCategory.None
                    },
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        AnimatedVisibility(
            visible = !isFiltered || filterCategory == FilterCategory.Playlists
        ) {
            LibraryTopAppBarOption(
                title = "Playlists",
                selected = filterCategory == FilterCategory.Playlists,
                onOptionClick = {
                    if (isFiltered) {
                        isFiltered = false
                        filterCategory = FilterCategory.None
                    } else {
                        isFiltered = true
                        filterCategory = FilterCategory.Playlists
                    }
                },
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
            )
        }

        AnimatedVisibility(
            visible = !isFiltered || filterCategory == FilterCategory.Podcasts
        ) {

            LibraryTopAppBarOption(
                title = "Podcasts",
                selected = filterCategory == FilterCategory.Podcasts,
                onOptionClick = {
                    if (isFiltered) {
                        isFiltered = false
                        filterCategory = FilterCategory.None
                    } else {
                        isFiltered = true
                        filterCategory = FilterCategory.Podcasts
                    }
                },
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
            )
        }

        AnimatedVisibility(
            visible = !isFiltered || filterCategory == FilterCategory.Albums
        ) {
            LibraryTopAppBarOption(
                title = "Albums",
                selected = filterCategory == FilterCategory.Albums,
                onOptionClick = {
                    if (isFiltered) {
                        isFiltered = false
                        filterCategory = FilterCategory.None
                    } else {
                        isFiltered = true
                        filterCategory = FilterCategory.Albums
                    }
                },
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
            )
        }
        AnimatedVisibility(
            visible = !isFiltered || filterCategory == FilterCategory.Artists
        ) {
            LibraryTopAppBarOption(
                title = "Artists",
                selected = filterCategory == FilterCategory.Artists,
                onOptionClick = {
                    if (isFiltered) {
                        isFiltered = false
                        filterCategory = FilterCategory.None
                    } else {
                        isFiltered = true
                        filterCategory = FilterCategory.Artists
                    }
                },
                modifier = Modifier.padding(horizontal = TopAppBarHorizontalPadding),
            )
        }
    }
}

@Composable
fun LibraryTopAppBarOption(
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
            .noRippleClickable {
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
fun SearchTopAppBar(
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = TopAppBarHorizontalPadding * 2),
        verticalAlignment = Alignment.Bottom,
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        start = SpotlightDimens.TopAppBarIconHorizontalPadding * 2,
                        end = SpotlightDimens.TopAppBarIconHorizontalPadding
                    )
                    .size(SpotlightDimens.TopAppBarIconSize)
                    .clip(shape = CircleShape)
                    .clickable { onAvatarClick() }
            )
            Text(
                text = stringResource(Res.string.search),
                style = SpotlightTextStyle.Text22W700,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
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
    isPlaying: Boolean,
    song: Song,
    currentPosition: Long,
    duration: Long,
    onPlayerClick: () -> Unit,
    onMainFunctionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                    text = song.title,
                    style = SpotlightTextStyle.Text11W400,
                    color = MaterialTheme.colorScheme.onBackground,
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

            Icon(
                painter = painterResource(if (isPlaying) SpotlightIcons.Pause else SpotlightIcons.Play),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize)
                    .align(Alignment.CenterEnd)
                    .noRippleClickable {
                        onMainFunctionClick()
                    }
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