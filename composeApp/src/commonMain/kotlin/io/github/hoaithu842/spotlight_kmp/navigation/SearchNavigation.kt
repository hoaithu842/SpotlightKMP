package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.hoaithu842.spotlight_kmp.presentation.screen.SearchResultScreen
import io.github.hoaithu842.spotlight_kmp.presentation.screen.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchGraph

fun NavController.navigateToSearch(navOptions: NavOptions? = null) =
    navigate(route = SearchGraph, navOptions = navOptions)

fun NavGraphBuilder.searchGraph(
    onAvatarClick: () -> Unit,
    onCancelClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,

    ) {
    navigation<SearchGraph>(
        startDestination = SearchRoute,
    ) {
        searchScreen(
            onAvatarClick = onAvatarClick,
            onNavigateToSearchClick = onNavigateToSearchClick,
        )
        searchResultScreen(
            onCancelClick = onCancelClick,
        )
    }
}

@Serializable
data object SearchRoute

fun NavGraphBuilder.searchScreen(
    onAvatarClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
) {
    composable<SearchRoute> {
        SearchScreen(
            onAvatarClick = onAvatarClick,
            onNavigateToSearchClick = onNavigateToSearchClick,
        )
    }
}

@Serializable
data object SearchResultRoute

fun NavController.navigateToSearchResultScreen(navOptions: NavOptions? = null) =
    navigate(route = SearchResultRoute, navOptions = navOptions)

fun NavGraphBuilder.searchResultScreen(
    onCancelClick: () -> Unit,
) {
    composable<SearchResultRoute> {
        SearchResultScreen(
            onCancelClick = onCancelClick,
        )
    }
}