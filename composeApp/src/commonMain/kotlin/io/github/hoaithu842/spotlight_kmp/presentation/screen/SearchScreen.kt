package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SearchTopAppBar
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightDimens

@Composable
fun SearchScreen(
    onAvatarClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        SearchTopAppBar(
            onAvatarClick = onAvatarClick,
            modifier = Modifier
                .statusBarsPadding()
                .height(SpotlightDimens.TopAppBarHeight)
        )
    }
}