package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray

@Composable
fun LibraryArtistItem(
    artist: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.LibraryItemHeight)
    ) {
        ArtistThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(SpotlightDimens.LibraryItemHeight)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.LibraryTextPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = artist,
                style = SpotlightTextStyle.Text16W400,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "abc",
                style = SpotlightTextStyle.Text11W400,
                color = NavigationGray,
                modifier = Modifier.padding(top = SpotlightDimens.LibraryTextPadding.times(0.5f))
            )
        }
    }
}

@Composable
fun LibraryPlaylistItem(
    creator: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.LibraryItemHeight)
    ) {
        LibraryPlaylistThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(SpotlightDimens.LibraryItemHeight)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.LibraryTextPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = creator,
                style = SpotlightTextStyle.Text16W400,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = "abc",
                style = SpotlightTextStyle.Text11W400,
                color = NavigationGray,
                modifier = Modifier.padding(top = SpotlightDimens.LibraryTextPadding.times(0.5f))
            )
        }
    }
}