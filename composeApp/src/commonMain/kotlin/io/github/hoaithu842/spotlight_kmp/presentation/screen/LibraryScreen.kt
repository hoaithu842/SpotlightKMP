package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.presentation.component.LibraryArtistItem
import io.github.hoaithu842.spotlight_kmp.presentation.component.LibraryPlaylistItem
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.FilterCategory
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.LibraryTopAppBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.recents

@Composable
fun LibraryScreen(
    onAvatarClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        var currentFilter by remember { mutableStateOf(FilterCategory.None) }

        LibraryTopAppBar(
            onAvatarClick = onAvatarClick,
            onNavigateToSearchClick = onNavigateToSearchClick,
            modifier = Modifier
                .statusBarsPadding()
                .height(SpotlightDimens.TopAppBarHeight * 2)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                SortAndViewBar(
                    modifier = Modifier.padding(
                        horizontal = SpotlightDimens.TopAppBarIconHorizontalPadding * 2,
                        vertical = SpotlightDimens.TopAppBarIconHorizontalPadding,
                    )
                )
            }

            item {
                LibraryArtistItem(
                    artist = "Justatee",
                    imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                    modifier = Modifier.padding(SpotlightDimens.TopAppBarHorizontalPadding),
                )
            }

            item {
                LibraryArtistItem(
                    artist = "Noo Phuoc Thinh",
                    imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                    modifier = Modifier.padding(SpotlightDimens.TopAppBarHorizontalPadding),
                )
            }

            item {
                LibraryPlaylistItem(
                    creator = "User",
                    imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                    modifier = Modifier.padding(SpotlightDimens.TopAppBarHorizontalPadding),
                )
            }

            item {
                LibraryArtistItem(
                    artist = "Justatee",
                    imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                    modifier = Modifier.padding(SpotlightDimens.TopAppBarHorizontalPadding),
                )
            }

            items(50) {
                LibraryArtistItem(
                    artist = "Justatee",
                    imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                    modifier = Modifier.padding(SpotlightDimens.TopAppBarHorizontalPadding),
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SpotlightDimens.MinimizedPlayerHeight)
                )
            }
        }
    }
}

@Composable
fun SortAndViewBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.TopAppBarOptionHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(SpotlightIcons.Search),
                contentDescription = "",
                modifier = Modifier.size(SpotlightDimens.LibraryFunctionBarIconSize),
            )
            Text(
                text = stringResource(Res.string.recents),
                style = SpotlightTextStyle.Text11W600,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = SpotlightDimens.TopAppBarIconHorizontalPadding)
            )
        }
        Icon(
            painter = painterResource(SpotlightIcons.Search),
            contentDescription = "",
            modifier = Modifier.size(SpotlightDimens.LibraryFunctionBarIconSize),
        )
    }
}