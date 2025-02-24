package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SearchResultTopAppBar
import io.github.hoaithu842.spotlight_kmp.ui.designsystem.SpotlightDimens

@Composable
fun SearchResultScreen(
    onCancelClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchResultTopAppBar(
            searchQuery = "",
            onCancelClick = onCancelClick,
            onSearchQueryChanged = {},
            modifier = Modifier
                .statusBarsPadding()
                .height(SpotlightDimens.TopAppBarHeight),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

        }
    }
}