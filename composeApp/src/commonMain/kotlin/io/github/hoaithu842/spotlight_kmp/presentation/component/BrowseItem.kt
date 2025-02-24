package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.TopAppBarGray

@Composable
fun BrowseItem(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.BrowseSectionHeight)
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(TopAppBarGray),
    ) {
        Text(
            text = title,
            style = SpotlightTextStyle.Text16W600,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .height(SpotlightDimens.BrowseSectionHeight)
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = SpotlightDimens.BrowseSectionThumbnailSize,
                )
        )
        EPThumbnail(
            imageUrl = imageUrl,
            modifier = modifier
                .align(Alignment.BottomEnd)
                .size(SpotlightDimens.BrowseSectionThumbnailSize)
                .rotate(30f)
                .offset(
                    x = SpotlightDimens.BrowseSectionThumbnailXOffset,
                    y = SpotlightDimens.BrowseSectionThumbnailYOffset,
                ),
        )
    }
}