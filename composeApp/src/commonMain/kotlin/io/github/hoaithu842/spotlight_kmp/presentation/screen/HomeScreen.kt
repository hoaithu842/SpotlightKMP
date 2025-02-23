package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.presentation.component.ArtistRecommendation
import io.github.hoaithu842.spotlight_kmp.presentation.component.EPRecommendation
import io.github.hoaithu842.spotlight_kmp.presentation.component.RecommendationSection
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.HomeScreenTab
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.HomeTopAppBar
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightDimens

@Composable
fun HomeScreen(
    onAvatarClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        var currentTab by remember { mutableStateOf(HomeScreenTab.All) }

        HomeTopAppBar(
            onAvatarClick = onAvatarClick,
            currentTab = currentTab,
            onTabClick = { tab ->
                if (tab != currentTab) {
                    currentTab = tab
                }
            },
            modifier = Modifier
                .statusBarsPadding()
                .height(SpotlightDimens.TopAppBarHeight)
        )

        when (currentTab) {
            HomeScreenTab.All -> AllTab()

            HomeScreenTab.Music -> Text(
                text = "Home - Music",
            )

            HomeScreenTab.Podcasts -> Text(
                text = "Home - Podcasts",
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllTab(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        // Recently Group
        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier.padding(SpotlightDimens.RecommendationPadding)
        ) {
            ArtistRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )

            EPRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )
            ArtistRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )

            EPRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )
            ArtistRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )

            EPRecommendation(
                artist = "Justatee",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .padding(SpotlightDimens.RecommendationPadding)
                    .weight(1f),
            )
        }

        RecommendationSection(
            title = "Today's biggest hits"
        )

        RecommendationSection(
            title = "Popular albums and singles"
        )

        RecommendationSection(
            title = "Charts"
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(SpotlightDimens.MinimizedPlayerHeight)
        )
    }
}