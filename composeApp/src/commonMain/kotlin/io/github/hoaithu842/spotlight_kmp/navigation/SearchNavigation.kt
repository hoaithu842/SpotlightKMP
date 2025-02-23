package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.hoaithu842.spotlight_kmp.presentation.screen.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

fun NavController.navigateToSearchScreen(navOptions: NavOptions? = null) =
    navigate(route = SearchRoute, navOptions = navOptions)

fun NavGraphBuilder.searchScreen(
    onAvatarClick: () -> Unit,
) {
    composable<SearchRoute> {
        SearchScreen(
            onAvatarClick = onAvatarClick,
        )
    }
}