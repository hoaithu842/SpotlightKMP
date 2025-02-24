package io.github.hoaithu842.spotlight_kmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.hoaithu842.spotlight_kmp.presentation.screen.LibraryScreen
import io.github.hoaithu842.spotlight_kmp.presentation.screen.LibrarySearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object LibraryGraph

fun NavController.navigateToLibrary(navOptions: NavOptions? = null) =
    navigate(route = LibraryGraph, navOptions = navOptions)

fun NavGraphBuilder.libraryGraph(
    onAvatarClick: () -> Unit,
    onCancelClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
) {
    navigation<LibraryGraph>(
        startDestination = LibraryRoute,
    ) {
        libraryScreen(
            onAvatarClick = onAvatarClick,
            onNavigateToSearchClick = onNavigateToSearchClick,
        )
        librarySearchScreen(
            onCancelClick = onCancelClick,
        )
    }
}

@Serializable
data object LibraryRoute

fun NavGraphBuilder.libraryScreen(
    onAvatarClick: () -> Unit,
    onNavigateToSearchClick: () -> Unit,
) {
    composable<LibraryRoute> {
        LibraryScreen(
            onAvatarClick = onAvatarClick,
            onNavigateToSearchClick = onNavigateToSearchClick,
        )
    }
}

@Serializable
data object LibrarySearchRoute

fun NavController.navigateToLibrarySearchScreen(navOptions: NavOptions? = null) =
    navigate(route = LibrarySearchRoute, navOptions = navOptions)

fun NavGraphBuilder.librarySearchScreen(
    onCancelClick: () -> Unit,
) {
    composable<LibrarySearchRoute> {
        LibrarySearchScreen(
            onCancelClick = onCancelClick
        )
    }
}