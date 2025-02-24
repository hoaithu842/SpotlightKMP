package io.github.hoaithu842.spotlight_kmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightIcons
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightTextStyle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spotlight.composeapp.generated.resources.Res
import spotlight.composeapp.generated.resources.what_do_you_want_to_listen

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpotlightDimens.SearchBarHeight)
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(MaterialTheme.colorScheme.onBackground),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    start = SpotlightDimens.TopAppBarIconHorizontalPadding.times(1.5f),
                    end = SpotlightDimens.TopAppBarIconHorizontalPadding,
                )
                .size(SpotlightDimens.HomeScreenDrawerHeaderOptionIconSize),
            painter = painterResource(SpotlightIcons.Search),
            tint = MaterialTheme.colorScheme.surface,
            contentDescription = "",
        )

        Text(
            text = stringResource(Res.string.what_do_you_want_to_listen),
            color = MaterialTheme.colorScheme.surface,
            style = SpotlightTextStyle.Text14W400,
        )
    }
}