package io.github.hoaithu842.spotlight_kmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import io.github.hoaithu842.spotlight_kmp.extensions.noRippleClickable
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import io.github.hoaithu842.spotlight_kmp.ui.theme.MinimizedPlayerBackground
import org.jetbrains.compose.resources.painterResource

@Composable
fun FullsizePlayer(
    onMinimizeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MinimizedPlayerBackground)
    ) {
        FullsizePlayerTopAppBar(
            artist = "Đen",
            onMinimizeClick = onMinimizeClick,
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = SpotlightDimens.FullsizePlayerTopAppBarPadding)
        )
    }
}

@Composable
fun FullsizePlayerTopAppBar(
    artist: String,
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
            text = artist,
            style = SpotlightTextStyle.Text11W400,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = SpotlightDimens.FullsizePlayerTopAppBarPadding),
        )
        Icon(
            painter = painterResource(SpotlightIcons.More),
            contentDescription = "",
            modifier = Modifier.size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize),
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}