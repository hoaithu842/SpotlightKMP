package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import io.github.hoaithu842.spotlight_kmp.extension.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.presentation.component.BrowseItem
import io.github.hoaithu842.spotlight_kmp.presentation.component.SearchBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SearchTopAppBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
    onAvatarClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        SearchTopAppBar(
            scrollBehavior = scrollBehavior,
            onAvatarClick = onAvatarClick,
        )

        SearchBar(
            modifier = Modifier
                .padding(
                    start = SpotlightDimens.RecommendationPadding * 2,
                    end = SpotlightDimens.RecommendationPadding * 2,
                    top = SpotlightDimens.SearchBarTopPadding,
                    bottom = SpotlightDimens.TopAppBarHorizontalPadding * 2,
                )
                .noRippleClickable { onNavigateToSearchClick() }
        )


        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .padding(SpotlightDimens.RecommendationPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = SpotlightDimens.MinimizedPlayerHeight),
        ) {
            BrowseItem(
                title = "Musics",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musics",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musics",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musics",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musics",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
            BrowseItem(
                title = "Musicsjfglisajglkjsdflkgjads;lgj;lsajdg;ljsadl;gjl;sa",
                imageUrl = "https://thantrieu.com/resources/arts/1078245010.webp",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(SpotlightDimens.RecommendationPadding),
            )
        }
    }
}