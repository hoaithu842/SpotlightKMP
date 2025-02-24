package io.github.hoaithu842.spotlight_kmp.ui.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import io.github.hoaithu842.spotlight_kmp.ui.theme.TopAppBarGray
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.add_account
import spotlight.composeapp.generated.resources.ic_launcher_background
import spotlight.composeapp.generated.resources.recents
import spotlight.composeapp.generated.resources.settings_and_privacy
import spotlight.composeapp.generated.resources.view_profile
import spotlight.composeapp.generated.resources.whats_new

enum class CustomDrawerState {
    Opened,
    Closed,
}

fun CustomDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun CustomDrawerState.opposite(): CustomDrawerState {
    return if (this == CustomDrawerState.Opened) CustomDrawerState.Closed
    else CustomDrawerState.Opened
}

@Composable
fun HomeScreenDrawer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TopAppBarGray)
            .safeDrawingPadding()
    ) {
        HomeScreenDrawerHeader(
            username = "Hoai Thu",
            onAvatarClick = {},
            modifier = Modifier.padding(vertical = SpotlightDimens.HomeScreenDrawerHeaderVerticalPadding)
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.15.dp,
            color = NavigationGray,
        )

        HomeScreenDrawerOption(
            icon = SpotlightIcons.Add,
            title = stringResource(Res.string.add_account),
            onOptionClick = {},
        )

        HomeScreenDrawerOption(
            icon = SpotlightIcons.Lightning,
            title = stringResource(Res.string.whats_new),
            onOptionClick = {},
        )

        HomeScreenDrawerOption(
            icon = SpotlightIcons.Clock,
            title = stringResource(Res.string.recents),
            onOptionClick = {},
        )

        HomeScreenDrawerOption(
            icon = SpotlightIcons.Settings,
            title = stringResource(Res.string.settings_and_privacy),
            onOptionClick = {},
        )
    }
}

@Composable
fun HomeScreenDrawerHeader(
    username: String,
    onAvatarClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = SpotlightDimens.HomeScreenDrawerHeaderPadding)
            .fillMaxWidth()
            .height(SpotlightDimens.TopAppBarHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .size(SpotlightDimens.HomeScreenDrawerHeaderIconSize)
                .clip(shape = CircleShape)
                .clickable { onAvatarClick() }
        )

        Column(
            modifier = Modifier.padding(start = SpotlightDimens.HomeScreenDrawerHeaderPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = username,
                style = SpotlightTextStyle.Text18W700,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
            Text(
                text = stringResource(Res.string.view_profile),
                style = SpotlightTextStyle.Text11W400,
                overflow = TextOverflow.Ellipsis,
                color = NavigationGray,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun HomeScreenDrawerOption(
    icon: DrawableResource,
    title: String,
    onOptionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(SpotlightDimens.HomeScreenDrawerHeaderPadding)
            .fillMaxWidth()
            .height(SpotlightDimens.HomeScreenDrawerHeaderOptionHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = Modifier.size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = title,
            style = SpotlightTextStyle.Text14W400,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            modifier = Modifier.padding(start = SpotlightDimens.HomeScreenDrawerHeaderPadding),
        )
    }
}