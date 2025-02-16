package io.github.hoaithu842.spotlight_kmp.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import io.github.hoaithu842.spotlight_kmp.ui.theme.NavigationGray
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SpotlightNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(SpotlightDimens.NavigationBarHeight)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        // Actual NavigationBar content
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent, // Transparent because we use the Box background
            content = content,
        )
    }
}

@Composable
fun RowScope.SpotlightNavigationBarItem(
    title: String,
    selected: Boolean,
    icon: DrawableResource,
    selectedIcon: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                Icon(
                    painter = painterResource(selectedIcon),
                    contentDescription = "",
                    modifier = Modifier.size(SpotlightDimens.NavigationBarIconSize),
                )
            } else {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "",
                    modifier = Modifier.size(SpotlightDimens.NavigationBarIconSize),
                )
            }
        },
        label = {
            Text(
                text = title,
                style = SpotlightTextStyle.Text11W400,
                overflow = TextOverflow.Ellipsis,
            )
        },
        colors = NavigationBarItemColors(
            selectedIndicatorColor = Color.Transparent,
            selectedIconColor = MaterialTheme.colorScheme.onBackground,
            selectedTextColor = MaterialTheme.colorScheme.onBackground,
            unselectedIconColor = NavigationGray,
            unselectedTextColor = NavigationGray,
            disabledIconColor = NavigationGray,
            disabledTextColor = NavigationGray,
        ),
        modifier = modifier,
    )
}