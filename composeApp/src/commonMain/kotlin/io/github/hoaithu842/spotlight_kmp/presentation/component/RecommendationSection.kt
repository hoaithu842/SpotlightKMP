package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray

@Composable
fun RecommendationSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.RecommendationSectionHeight)
    ) {
        Text(
            text = title,
            style = SpotlightTextStyle.Text22W700,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
            RecommendationItem(
                imageUrl = "https://thantrieu.com/resources/arts/1078245023.webp",
                description = "The Weeknd, Lady Gaga, JENNIE, Charlie Puth, yung kai, Dhruv",
            )
        }
    }
}

@Composable
fun RecommendationItem(
    imageUrl: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp),
    ) {
        PlaylistThumbnail(
            imageUrl = imageUrl,
            modifier = Modifier.size(SpotlightDimens.RecommendationSectionThumbnailSize)
        )

        Text(
            text = description,
            style = SpotlightTextStyle.Text14W400,
            color = NavigationGray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(SpotlightDimens.RecommendationSectionThumbnailSize)
        )
    }
}