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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.FilterCategory
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.LibraryTopAppBar
import io.github.hoaithu842.spotlight_kmp.presentation.designsystem.SpotlightDimens

@Composable
fun LibraryScreen(
    onAvatarClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        var currentFilter by remember { mutableStateOf(FilterCategory.None) }

        LibraryTopAppBar(
            onAvatarClick = onAvatarClick,
            modifier = Modifier
                .statusBarsPadding()
                .height(SpotlightDimens.TopAppBarHeight * 2)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

        }
    }
}