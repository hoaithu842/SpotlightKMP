package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.TopAppBarGray

@Composable
fun ArtistRecommendation(
    artist: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.ThumbnailSize)
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(TopAppBarGray),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ArtistThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(SpotlightDimens.ThumbnailSize)
                .clip(shape = RoundedCornerShape(size = 6.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(1.dp)
        )
        Text(
            text = artist,
            style = SpotlightTextStyle.Text11W600,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.RecommendationTextPadding)
                .fillMaxWidth()
        )
    }
}

@Composable
fun EPRecommendation(
    artist: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.ThumbnailSize)
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(TopAppBarGray),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EPThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(SpotlightDimens.ThumbnailSize)
        )
        Text(
            text = artist,
            style = SpotlightTextStyle.Text11W600,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = SpotlightDimens.RecommendationTextPadding)
                .fillMaxWidth()
        )
    }
}